package grid;

public class LongestIncreasingPathInMatrix {

    //https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
    
    //Time complexity : O(m * n)
    public int longestIncreasingPath(int[][] grid)
    {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        int maxLen = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // longest increasing path starting from cell(i,j)
                maxLen = Math.max(maxLen, dfs(grid, dp, i, j, m, n));
            }
        }

        return maxLen;
    }

    private static final int[][] DIRECTIONS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    private static int dfs(int[][] grid, int[][] dp, int i, int j, int m, int n)
    {
        if(dp[i][j] != 0){
            return dp[i][j]; //already computed
        }

        int max = 1; //every cell itself forms path length 1

        for(int[] dir : DIRECTIONS) {
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]) {
                max = Math.max(max, 1 + dfs(grid, dp, x, y, m, n));
            }
        }

        dp[i][j] = max;
        return dp[i][j];
    }
}
