package fifo;

import java.text.DecimalFormat;
import java.util.*;

public class FifoCalculation {
    
    private String calculateTax(List<String> trades)
    {
        Map<String, List<String>> tickerToTradesMap = this.getTickerToTradesMap(trades);

        double profit = 0.0;

        for(String tickerSymbol : tickerToTradesMap.keySet()){
            List<String> tradeList = tickerToTradesMap.get(tickerSymbol);
            Deque<Pair> longQueue = new LinkedList<>();
            Deque<Pair> shortQueue = new LinkedList<>();

            for(String trade : tradeList){
                int quantity = Integer.parseInt(trade.split(",")[3]);
                double price = Double.parseDouble(trade.split(",")[4]);

                if(isBuy(trade)){
                    //match if any short
                    while(quantity > 0 && !shortQueue.isEmpty()){
                        Pair shortTrade = shortQueue.poll();

                        if(quantity >= shortTrade.getQuantity()){
                            profit += shortTrade.getQuantity() * (shortTrade.getPrice() - price);
                            quantity = quantity - shortTrade.getQuantity();
                        }else {
                            profit += quantity * (shortTrade.getPrice() - price);

                            int remainingQty = shortTrade.getQuantity() - quantity;
                            shortQueue.addFirst(new Pair(remainingQty, shortTrade.getPrice()));

                            quantity = quantity - shortTrade.getQuantity();
                        }
                    }

                    if(quantity > 0){
                        longQueue.add(new Pair(quantity, price));
                    }

                } else if (isSell(trade)) {
                    //match with buy
                    while (quantity > 0 && !longQueue.isEmpty()){
                        Pair longTrade = longQueue.poll();

                        if(quantity >= longTrade.getQuantity()){
                            profit += longTrade.getQuantity() * (price - longTrade.getPrice());
                            quantity = quantity - longTrade.getQuantity();
                        }else {
                            profit += quantity * (price - longTrade.getPrice());
                            //quantity = quantity - longTrade.getQuantity();
                            int remainingQty = longTrade.getQuantity() - quantity;

                            longQueue.addFirst(new Pair(remainingQty, longTrade.getPrice()));

                            quantity = quantity - longTrade.getQuantity();
                        }
                    }

                    //add to short queue
                    if(quantity > 0){
                        shortQueue.add(new Pair(quantity, price));
                    }
                }
                System.out.println("profit ###= "+profit);
            }
        }

        System.out.println("PROFIT ======"+profit);

        //calculate tax
        double taxPercentage = 0.25;
        double taxCalculated = taxPercentage * profit;

        return this.convertTaxToStringFormat(taxCalculated);
    }

    private String convertTaxToStringFormat(double taxCalculated)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        String tax = df.format(taxCalculated);
        String taxInDollar = "$"+tax;

        //if negative, put paranthesis
        if(taxInDollar.contains("-")){
            taxInDollar = "("+taxInDollar+")";
            taxInDollar = taxInDollar.replace("-", "");
        }
        return taxInDollar;
    }

    private Map<String, List<String>> getTickerToTradesMap(List<String> trades)
    {
        Map<String, List<String>> tickerToTradesMap = new HashMap<>();
        for(String trade : trades){
            String tickerSymbol = trade.split(",")[1];
            List<String> list = tickerToTradesMap.getOrDefault(tickerSymbol, new ArrayList<>());
            list.add(trade);
            tickerToTradesMap.put(tickerSymbol, list);
        }
        return tickerToTradesMap;
    }

    private boolean isBuy(String trade)
    {
        return trade.split(",")[2].equals("B");
    }

    private boolean isSell(String trade)
    {
        return trade.split(",")[2].equals("S");
    }

    public static void main(String[] args)
    {
        FifoCalculation fifo = new FifoCalculation();

        List<String> trades = new ArrayList<>();

        trades.add("2015-01-01,AAPL,B,50,80.0");
        trades.add("2015-01-01,AAPL,B,60,100.0");
        trades.add("2015-01-01,AAPL,S,70,130.0");
        trades.add("2015-01-01,AAPL,S,10,90.0");
        trades.add("2015-01-01,AAPL,S,80,120.0");
        trades.add("2015-01-01,AAPL,B,10,70.0");
        trades.add("2015-01-01,AAPL,B,70,160.0");

        System.out.println("Tax ======= "+fifo.calculateTax(trades));
    }

    class Pair{
        int quantity;
        double price;

        public Pair(int quantity, double price) {
            this.quantity = quantity;
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }
    }
}
