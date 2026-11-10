package greedy;

public class BestTimeToBuyAndSellStockiii {

    public int maxProfit(int[] prices) {

        if (prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;

        int[] leftProfit = new int[n];
        int[] rightProfit = new int[n];

        // First pass: Left to right
        int buy = 0;
        int maxProfit = 0;

        for (int sell = 1; sell < n; sell++) {
            maxProfit = Math.max(maxProfit, prices[sell] - prices[buy]);
            leftProfit[sell] = maxProfit;

            if (prices[sell] < prices[buy]) {
                buy = sell;
            }
        }

        // Second pass: Right to left
        int sell = n - 1;
        maxProfit = 0;

        for (int buyIndex = n - 2; buyIndex >= 0; buyIndex--) {
            maxProfit = Math.max(maxProfit, prices[sell] - prices[buyIndex]);
            rightProfit[buyIndex] = maxProfit;

            if (prices[buyIndex] > prices[sell]) {
                sell = buyIndex;
            }
        }

        // Combine both transactions
        int result = 0;

        for (int i = 0; i < n; i++) {
            result = Math.max(
                    result,
                    leftProfit[i] //Best first transaction ending on or before day i
                            + rightProfit[i] //Best second transaction starting on or after day i
            );
        }

        return result;
    }
}
