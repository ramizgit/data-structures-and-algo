package dp;

public class LongestCommonSubstring {
    public static void main(String[] args)
    {
        System.out.println(findLongestCommonSubstring("abcdef", "abcdefgh"));
    }

    public static int findLongestCommonSubstring(String str1, String str2)
    {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m+1][n+1]; //initialized with all zero
        int maxCommonSubStrLength = 0;
        int maxIdxRow = 0;
        int maxIdxCol = 0;

        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];

                    //update max length seen so far
                    if(dp[i][j] > maxCommonSubStrLength){
                        maxCommonSubStrLength = dp[i][j];
                        maxIdxRow = i;
                        maxIdxCol = j;
                    }
                }else {
                    dp[i][j] = 0;
                }
            }
        }

        //print longest common subsequence
        StringBuilder sb = new StringBuilder();
        while(dp[maxIdxRow][maxIdxCol] > 0){
            sb.append(str1.charAt(maxIdxRow-1));
            maxIdxRow--;
            maxIdxCol--;
        }
        System.out.println("longest common substring is : "+ sb.reverse().toString());

        return maxCommonSubStrLength;
    }
}
