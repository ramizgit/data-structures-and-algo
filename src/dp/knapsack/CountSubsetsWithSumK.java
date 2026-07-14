package dp.knapsack;

public class CountSubsetsWithSumK {

    /*
    Given an array nums of non-negative integers and an integer k, the task is to count all subsets of the given array with a sum equal to k.
     */

    private static int countSubsetsWithSumK(int[] nums, int target)
    {
        int[] dp = new int[target + 1];

        dp[0] = 1; // One way to make sum 0: choose no elements

        for (int n : nums) {
            for (int t = target; t >= n; t--) { // Backward loop since each element can be used at most once

                dp[t] = dp[t] //existing ways
                        + dp[t - n]; //new ways by taking the current num
            }
        }

        return dp[target];
    }
}
