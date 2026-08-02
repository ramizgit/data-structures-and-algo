package grid.dfs;

public class LongestIncreasingPath {

    //https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/description/

    private static final int[][] DIRECTIONS = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    private static int countPaths(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int maxLen = 0;
        int[][] dp = new int[m][n]; //dp[i][j] = longest increasing path starting from (i, j)

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                maxLen = Math.max(maxLen, dfs(grid, dp, m, n, i, j));
            }
        }

        return maxLen;
    }

    private static int dfs(int[][] grid, int[][] dp, int m, int n, int i, int j)
    {
        if(dp[i][j] != 0){
            return dp[i][j];
        }

        int max = 1; //every cell itself counts

        for(int[] dir : DIRECTIONS){
            int x = dir[0] + i;
            int y = dir[1] + j;

            if(x >= 0 && x < m && y >= 0 && y < n //boundary check
                    && grid[x][y] > grid[i][j] //constraint check
            )
            {
                max = Math.max(max, 1 + dfs(grid, dp, m, n, x, y));
            }
        }

        dp[i][j] = max; //memoize longest increasing path starting from (i, j)
        return max;
    }
}
