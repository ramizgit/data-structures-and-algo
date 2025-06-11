package dp;

public class UniquePaths {
    //https://leetcode.com/problems/unique-paths/description/
    public static void main(String[] args)
    {
        System.out.println(uniquePaths(3, 7)); //28
        System.out.println(uniquePaths(3, 2)); //3
    }

    private static int uniquePaths(int m, int n)
    {
        int[][] dp = new int[m][n];

        //populate first column
        for(int i=0; i<m; i++){
            dp[i][0] = 1;
        }

        //populate first row
        for(int j=0; j<n; j++){
            dp[0][j] = 1;
        }

        //run dp
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}
