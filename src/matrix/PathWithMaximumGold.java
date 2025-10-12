package grid;

public class PathWithMaximumGold {
    //https://leetcode.com/problems/path-with-maximum-gold/description/

    public static void main(String[] args)
    {
        int[][] grid = {{0,6,0},{5,8,7},{0,9,0}};
        System.out.print(maxGold(grid));
    }

    private static int maxGold(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int max = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] != 0){
                    max = Math.max(max, dfs(grid, visited, m, n, i, j));
                }
            }
        }

        return max;
    }

    private static int dfs(int[][] grid, boolean[][] visited, int m, int n, int i, int j)
    {
        visited[i][j] = true;

        int maxNeighbour = 0;

        int[][] directions = { {0, -1}, {0, 1}, {1, 0}, {-1, 0} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] != 0 && !visited[x][y]){
                maxNeighbour = Math.max(maxNeighbour, dfs(grid, visited, m, n, x, y));
            }
        }

        visited[i][j] = false; //backtrack

        return grid[i][j] + maxNeighbour;
    }
}
