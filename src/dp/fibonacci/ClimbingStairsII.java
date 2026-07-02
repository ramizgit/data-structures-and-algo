package dp.fibonacci;

import java.util.Arrays;

public class ClimbingStairsII {

    //https://leetcode.com/problems/climbing-stairs-ii/description/

    // Time : O(n)
    // Space: O(n)
    public int climbStairs(int n, int[] cost)
    {
        int[] dp = new int[n + 1]; //dp[i] = min cost to reach step i
        Arrays.fill(dp, Integer.MAX_VALUE); //initialize with high value

        dp[0] = 0; //base case

        for (int step = 1; step <= n; step++) {

            //try reaching the current step using a jump of length 1, 2 or 3
            for (int jump = 1; jump <= 3; jump++) {

                int prevStep = step - jump;

                if (prevStep >= 0 && dp[prevStep] != Integer.MAX_VALUE) {

                    dp[step] = Math.min(
                            dp[step], //best answer found so far

                            dp[prevStep] //minimum cost to reach previous step
                                    + cost[step - 1] //landing cost of current step (steps are 1-based, array is 0-based, hence -1)
                                    + (step - prevStep) * (step - prevStep) //jump cost
                    );
                }
            }
        }

        return dp[n];
    }
}
