package dp;

import java.util.Arrays;

public class HouseRobberII {

    //https://leetcode.com/problems/house-robber-ii/description/

    public static void main(String[] args)
    {
        System.out.println(rob(new int[]{2,3,2})); //3
        System.out.println(rob(new int[]{1,2,3,1})); //4
        System.out.println(rob(new int[]{1,2,3})); //3
    }

    private static int rob(int[] nums)
    {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.min(nums[0], nums[1]);
        }

        return Math.max(robberHelper(Arrays.copyOfRange(nums, 0, nums.length-1)),
                        robberHelper(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    private static int robberHelper(int[] arr)
    {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for(int i=2; i<arr.length; i++){
            dp[i] = Math.max(dp[i-2] + arr[i], dp[i-1]);
        }

        return dp[dp.length-1];
    }
}
