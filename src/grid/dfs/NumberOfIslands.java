package consistenthashing.grid.dfs;

public class NumberOfIslands {

    private static final int[][] DIRECTIONS = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };

    public int numOfIslands(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        //dfs logic
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    count++;
                    dfs(grid, visited, i, j, m, n);
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, boolean[][] visited, int i, int j, int m, int n)
    {
        visited[i][j] = true;

        //explore all possible directions
        for(int[] dir : DIRECTIONS){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >= 0 && y < n //boundary check
                    && grid[x][y] == 1 //land check
                    && !visited[x][y]) //visited check
            {
                dfs(grid, visited, x, y, m, n);
            }
        }
    }
}
