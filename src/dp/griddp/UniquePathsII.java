package dp.griddp;

public class UniquePathsII {

    //https://leetcode.com/problems/unique-paths-ii/

    private static int uniquePaths(int[][] grid)
    {
        //input validation
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        //edge case : early return if start or end is blocked
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n]; //dp[i][j] = number of unique paths to reach cell (i, j)

        dp[0][0] = 1; //base case

        //first row
        for(int i=1; i<n; i++){
            if(grid[0][i] == 1){
                dp[0][i] = 0;
            }else{
                dp[0][i] = dp[0][i-1];
            }
        }

        //first column
        for(int i=1; i<m; i++){
            if(grid[i][0] == 1){
                dp[i][0] = 0;
            }else{
                dp[i][0] = dp[i-1][0];
            }
        }

        //rest of dp
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(grid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }
}
