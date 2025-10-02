package dp;

public class LongestCommonSubsequence {
    public static void main(String[] args)
    {
        findLongestCommonSubsequence("abcdefyz", "abcdghefxz"); //7
    }

    public static int findLongestCommonSubsequence(String s1, String s2)
    {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m+1][n+1];
        int max = Integer.MIN_VALUE;

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];

                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }

                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
