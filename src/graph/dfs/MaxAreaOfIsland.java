package consistenthashing.graph.dfs;

public class MaxAreaOfIsland {
    //https://leetcode.com/problems/max-area-of-island/description/

    private static int[][] DIRECTIONS = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public int maxAreaOfIsland(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        //dfs logic
        boolean[][] visited = new boolean[m][n]; //to prevent revisiting same cells during dfs traversal
        int maxArea = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    int[] count = new int[1];
                    dfs(grid, visited, i, j, m, n, count);
                    maxArea = Math.max(maxArea, count[0]);
                }
            }
        }

        return maxArea;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int i, int j, int m, int n, int[] count)
    {
        visited[i][j] = true;
        count[0]++; //increment area counter

        //explore all feasible directions
        for(int[] dir : DIRECTIONS){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 1){
                dfs(grid, visited, x, y, m, n, count);
            }
        }
    }
}
