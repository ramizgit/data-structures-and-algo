package google;

public class MaximumNumberOfPointsWithCost {

    // https://leetcode.com/problems/maximum-number-of-points-with-cost/

    //todo : practice

    public long maxPoints(int[][] points)
    {
        int m = points.length;
        int n = points[0].length;

        // dp[i] = maximum score achievable when reaching column i in the current processed row
        long[] dp = new long[n];

        // initialize first row
        for(int i = 0; i < n; i++){
            dp[i] = points[0][i];
        }

        // process remaining rows
        for(int i = 1; i < m; i++) {

            // left-to-right scan on prev dp row
            long[] leftMax = new long[n];
            leftMax[0] = dp[0];
            for(int j = 1; j < n; j++){
                leftMax[j] = Math.max(dp[j], leftMax[j - 1] - 1);
            }

            // right-to-left scan on prev dp row
            long[] rightMax = new long[n];
            rightMax[n - 1] = dp[n - 1];
            for(int j = n - 2; j >= 0; j--){
                rightMax[j] = Math.max(dp[j], rightMax[j + 1] - 1);
            }

            // build current row dp
            long[] curr = new long[n];
            for(int j = 0; j < n; j++){
                curr[j] = points[i][j] + Math.max(leftMax[j], rightMax[j]);
            }

            dp = curr;
        }

        // answer = maximum in last dp row
        long ans = 0;

        for(long val : dp){
            ans = Math.max(ans, val);
        }

        return ans;
    }
}
