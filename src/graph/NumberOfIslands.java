package graph;

public class NumberOfIslands {
    public static void main(String[] args)
    {
        int[][] grid = {{0,1,0,0},
                        {1,0,1,0},
                        {0,0,0,0},
                        {1,1,0,0}};
        System.out.println(numOfIslands(grid)); //4
    }

    private static int numOfIslands(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int result = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    dfs(grid, visited, i, j, m, n);
                    result++;
                }
            }
        }

        return result;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int i, int j, int m, int n)
    {
        visited[i][j] = true;

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 1){
                dfs(grid, visited, x, y, m, n);
            }
        }
    }
}
