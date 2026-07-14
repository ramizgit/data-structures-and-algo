package consistenthashing.dp.knapsack;

public class TargetSum {

    //https://leetcode.com/problems/target-sum/description/

    /*
    // Let P = sum of numbers assigned '+' (+ve group)
    // Let N = sum of numbers assigned '-' (-ve group)
    //
    // P - N = target
    // P + N = totalSum
    //
    // => 2P = totalSum + target
    // => P = (totalSum + target) / 2
    //
    // So the problem reduces to:
    // Count subsets whose sum is (totalSum + target) / 2.
     */

    public int findTargetSumWays(int[] nums, int target)
    {
        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }

        if (Math.abs(target) > totalSum)
            return 0;

        if ((totalSum + target) % 2 != 0)
            return 0;

        int requiredSum = (totalSum + target) / 2;

        return countSubsetsWithSumK(nums, requiredSum);
    }

    private int countSubsetsWithSumK(int[] nums, int target)
    {
        int[] dp = new int[target + 1];

        dp[0] = 1; // base case : 1 way to make sum 0 by choosing no elements

        for (int n : nums) {
            for (int t = target; t >= n; t--) { //backward loop since each element can be used at most once

                dp[t] = dp[t] //existing ways (without current number)
                        + dp[t - n]; //new ways created by taking the current number
            }
        }

        return dp[target];
    }
}
