package dp;

import java.util.Arrays;

public class LongestIncreasingSubSeq {
    //https://leetcode.com/problems/longest-increasing-subsequence/description/

    public static void main(String[] args)
    {
        System.out.println(longestISS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(longestISS(new int[]{7,7,7,7,7}));
    }

    private static int longestISS(int[] num)
    {
        int[] dp = new int[num.length];
        Arrays.fill(dp, 1);
        int max = 1;

        //traverse the array backward (from end) and populate dp
        for(int i=num.length-1; i>=0; i--){
            for(int j=i+1; j<num.length; j++){
                if(num[i] < num[j]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }
}
