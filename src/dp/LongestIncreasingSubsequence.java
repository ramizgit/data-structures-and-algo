package dp;

public class LongestIncreasingSubsequence {
    //https://leetcode.com/problems/longest-increasing-subsequence/description/
    public static void main(String[] args)
    {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})); //4
        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3,5})); //5
        System.out.println(lengthOfLIS(new int[]{7,7,7,7,7,7,7})); //1
    }

    private static int lengthOfLIS(int[] nums)
    {
        int n = nums.length;
        int[] dp = new int[n];
        for(int i=0; i<n; i++){
            dp[i] = 1;
        }

        for(int right=1; right<n; right++){
            int left = 0;
            while (left < right){
                if(nums[left] < nums[right]){
                    if(dp[right] <= dp[left]){
                        dp[right] = dp[left] + 1;
                    }
                }
                left++;
            }
        }

        int result = 0;
        for(int i=0; i<n; i++){
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
