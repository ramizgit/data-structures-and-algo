package fifo;

import java.text.DecimalFormat;
import java.util.*;

public class FifoCalculation {

    /*
    2015-01-01,AAPL,B,50,100.0
    2015-01-01,AAPL,B,50,100.0
    2015-01-01,AAPL,B,50,100.0
    2015-01-01,AAPL,B,50,100.0
     */
    private String calculateTax(List<String> trades)
    {
        Map<String, List<String>> tickerToTradesMap = new HashMap<>();
        for(String trade : trades){
            String tickerSymbol = trade.split(",")[1];
            List<String> list = tickerToTradesMap.getOrDefault(tickerSymbol, new ArrayList<>());
            list.add(trade);
            tickerToTradesMap.put(tickerSymbol, list);
        }

        Deque<Pair> longQueue = new LinkedList<>();
        Deque<Pair> shortQueue = new LinkedList<>();
        double profit = 0.0;

        for(String tickerSymbol : tickerToTradesMap.keySet()){
            List<String> tradeList = tickerToTradesMap.get(tickerSymbol);

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
                            quantity = quantity - longTrade.getQuantity();
                            int remainingQty = longTrade.getQuantity() - quantity;

                            longQueue.addFirst(new Pair(remainingQty, longTrade.getPrice()));
                        }
                    }

                    //add to short queue
                    if(quantity > 0){
                        shortQueue.add(new Pair(quantity, price));
                    }
                }
            }
        }

        System.out.println("PROFIT ======"+profit);

        //calculate tax
        double taxPercentage = 0.25;
        double totalTax = taxPercentage * profit;
        DecimalFormat df = new DecimalFormat("0.00");
        String tax = df.format(totalTax);
        String taxInDollar = "$"+tax;

        //if negative, put paranthesis
        if(taxInDollar.contains("-")){
            taxInDollar = "("+taxInDollar+")";
            taxInDollar = taxInDollar.replace("-", "");
            //System.out.println(replace);
        }

        System.out.println(taxInDollar);
        return taxInDollar;
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

        trades.add("2015-01-01,AAPL,B,50,100.0");
        trades.add("2015-01-01,AAPL,S,60,130.0");
        trades.add("2015-01-01,AAPL,B,20,120.0");

        /*
        50 * (130-100) = 1500
        10 * (130-120) = 100
        profit = 1600
         */

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

