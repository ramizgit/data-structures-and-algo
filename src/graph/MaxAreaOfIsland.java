package graph;

public class MaxAreaOfIsland {
    //https://leetcode.com/problems/max-area-of-island/description/
    public static void main(String[] args)
    {
        int[][] grid = {{0,1,0,0},
                        {1,0,1,0},
                        {0,0,0,1},
                        {1,1,1,1}};
        System.out.println(maxAreaOfIsland(grid)); //5
    }

    private static int maxAreaOfIsland(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int result = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    int[] count = new int[1];
                    dfs(grid, visited, i, j, m, n, count);
                    result = Math.max(result, count[0]);
                }
            }
        }

        return result;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int i, int j, int m, int n, int[] count)
    {
        visited[i][j] = true;
        count[0]++;

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 1){
                dfs(grid, visited, x, y, m, n, count);
            }
        }
    }
}
