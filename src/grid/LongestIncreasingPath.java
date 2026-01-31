package grid;

public class LongestIncreasingPath {
    //https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/description/

    public static void main(String[] args)
    {
        int[][] grid = { {1, 1}, {3, 4} };
        System.out.println(countPaths(grid));
    }

    private static int countPaths(int[][] grid)
    {
        if(grid == null){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;
        int maxLen = 0;
        int[][] dp = new int[m][n];

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

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        int max = 1; //every cell itself counts

        for(int[] dir : directions){
            int x = dir[0] + i;
            int y = dir[1] + j;

            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]){
                max = Math.max(max, 1 + dfs(grid, dp, m, n, x, y));
            }
        }

        dp[i][j] = max;
        return dp[i][j];
    }
}
