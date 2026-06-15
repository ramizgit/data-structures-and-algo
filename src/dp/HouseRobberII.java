package dp;

import java.util.Arrays;

public class HouseRobberII {

    //https://leetcode.com/problems/house-robber-ii/description/

    public int rob(int[] nums)
    {
        //input validation
        if(nums == null || nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return nums[0];
        }

        if(nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(
                robberHelper(nums, 0, nums.length - 2), // include first house, exclude last house
                robberHelper(nums, 1, nums.length - 1) // exclude first house, include last house
        );
    }

    private int robberHelper(int[] nums, int start, int end)
    {
        int length = end - start + 1;

        int[] dp = new int[length];

        // base cases
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        for(int i = 2; i < length; i++){

            dp[i] = Math.max(
                    nums[start + i] + dp[i - 2], // rob current house
                    dp[i - 1]                    // skip current house
            );
        }

        return dp[length - 1];
    }
}
