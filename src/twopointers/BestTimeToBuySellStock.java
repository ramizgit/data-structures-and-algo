package twopointer;

public class BestTimeToBuySellStock {

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

    public static void main(String[] args)
    {
        System.out.println(maxProfit(new int[]{7,1,5,3,9,4})); //8
        System.out.println(maxProfit(new int[]{7,5,4,3})); //0
    }

    private static int maxProfit(int[] prices)
    {
        int buy = 0;
        int sell = 1;
        int maxProfit = Integer.MIN_VALUE;

        while (sell < prices.length){

            maxProfit = Math.max(maxProfit, prices[sell] - prices[buy]);

            //reset if selling price is lower
            if(prices[sell] < prices[buy]){
                buy = sell;
                sell = sell + 1;
            }

            sell++;
        }

        return Math.max(maxProfit, 0);
    }
}
