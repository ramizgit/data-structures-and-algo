package dp;

public class LongestCommonSubsequence {
    public static void main(String[] args)
    {
        findLongestCommonSubsequence("abcdefyz", "abcdghefxz"); //7
    }

    public static void findLongestCommonSubsequence(String s1, String s2)
    {
        int row = s1.length()+1;
        int col = s2.length()+1;

        //dp matrix
        int[][] dp = new int[row][col];
        int maxCommonSubSeqLen = 0;

        //set first row to zero
        for(int i=0; i<col; i++){
            dp[0][i] = 0;
        }

        //set first col to zero
        for(int i=0; i<row; i++){
            dp[i][0] = 0;
        }

        for(int i=1; i<row; i++){
            for(int j=1; j<col; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    //if same, diagonal+1
                    dp[i][j] = dp[i-1][j-1]+1;

                    //update max len
                    maxCommonSubSeqLen = Math.max(maxCommonSubSeqLen, dp[i][j]);
                }else {
                    //else, max of left or top neighbor
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        System.out.println("maxCommonSubSeqLen : "+maxCommonSubSeqLen);
    }
}
