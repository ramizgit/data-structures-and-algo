package consistenthashing.graph.dfs;

public class IslandPerimeter {

    //https://leetcode.com/problems/island-perimeter/description/

    private static int[][] DIRECTIONS = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public int islandPerimeter(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        //dfs logic
        boolean[][] visited = new boolean[m][n];
        int[] perimeter = new int[1];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){ //the problem guarantees exactly one island, hence no visited check here like we do in number of islands problem
                    dfs(grid, visited, i, j, m, n, perimeter);
                    return perimeter[0];
                }
            }
        }

        return 0;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int i, int j, int m, int n, int[] perimeter)
    {
        visited[i][j] = true;

        //explore all possible directions
        for(int[] dir : DIRECTIONS){
            int x = i + dir[0];
            int y = j + dir[1];

            // If out of bounds or water, it's a perimeter edge
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                // this side of the land cell touches either boundary or the water, hence increment perimeter
                perimeter[0]++; //water or out of bound → contributes to perimeter
            } else if (!visited[x][y]) {
                dfs(grid, visited, x, y, m, n, perimeter); //else if land and not visited, keep exploring
            }
        }
    }
}
