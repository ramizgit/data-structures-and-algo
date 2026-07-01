package consistenthashing.dp.knapsack;

import java.util.Arrays;

public class PerfectSquares {

    //https://leetcode.com/problems/perfect-squares/description/

    // SAME AS COIN CHANGE PROBLEM
    // Unbounded Knapsack (Coin Change): each perfect square can be used unlimited times.
    private static int numSquares(int n)
    {
        int[] dp = new int[n+1]; // dp[t] = minimum number of perfect squares that sum to t
        Arrays.fill(dp, n+1); //infinity
        dp[0] = 0; //base case

        for (int i = 1; i * i <= n; i++) { //iterate all valid squares

            int square = i * i;

            for(int t=square; t<=n; t++){ //forward loop - unbounded knapsack

                dp[t] = Math.min(
                        dp[t], //dont pick
                        1 + dp[t - square] //pick
                );
            }
        }

        return dp[n] == n+1 ? -1 : dp[n];
    }
}
