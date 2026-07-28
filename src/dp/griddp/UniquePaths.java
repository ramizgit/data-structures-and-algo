package dp.griddp;

public class UniquePaths {

    //https://leetcode.com/problems/unique-paths/description/

    private static int uniquePaths(int m, int n)
    {
        int[][] dp = new int[m][n]; //dp[i][j] = number of unique paths to reach cell (i, j)

        //populate first dp column
        for(int i=0; i<m; i++){
            dp[i][0] = 1;
        }

        //populate first dp row
        for(int j=0; j<n; j++){
            dp[0][j] = 1;
        }

        //populate rest of dp
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}
