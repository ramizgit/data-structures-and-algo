package grid;

public class LongestIncreasingPathInMatrix {
    //https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
    public static void main(String[] args)
    {
        int[][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        System.out.println("Longest Increasing Path: " + longestIncreasingPath(matrix)); // Output: 4 (Path: 1 → 2 → 6 → 9)
    }

    private static int longestIncreasingPath(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        int[][] memo = new int[m][n];
        int max = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                max = Math.max(max, dfs(grid, memo, m, n, i, j));
            }
        }
        return max;
    }

    //dfs
    private static int dfs(int[][] grid, int[][] memo, int m, int n, int i, int j)
    {
        if(memo[i][j] != 0){
            return memo[i][j];
        }

        int max = 1;
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] > grid[i][j]){
                max = Math.max(max, 1 + dfs(grid, memo, m, n, x, y));
            }
        }

        memo[i][j] = max;
        return max;
    }
}
