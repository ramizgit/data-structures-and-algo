package twopointers;

public class BestTimeToBuySellStock {

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

    public int maxProfit(int[] prices)
    {
        //input validation
        if(prices == null || prices.length < 2){
            return 0;
        }

        int buy = 0; //assume buy on first day
        int maxProfit = 0;

        for(int sell = 1; sell < prices.length; sell++){

            maxProfit = Math.max(maxProfit, prices[sell] - prices[buy]);

            //reset if a lower buying price is found
            if(prices[sell] < prices[buy]){
                buy = sell;
            }
        }

        return maxProfit;
    }
}
