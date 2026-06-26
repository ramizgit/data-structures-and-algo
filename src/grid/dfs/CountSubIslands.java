package consistenthashing.grid.dfs;

public class CountSubIslands {

    //https://leetcode.com/problems/count-sub-islands/description/

    private static final int[][] DIRECTIONS = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };

    public int countSubIslands(int[][] grid1, int[][] grid2)
    {
        // Traverse each island in grid2 using DFS.
        // An island is a sub-island only if every visited land cell also exists as land in grid1.

        int m = grid2.length;
        int n = grid2[0].length;

        boolean[][] visited = new boolean[m][n];

        int subIslandsCount = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){

                if(grid2[i][j] == 1 && !visited[i][j]){
                    if(isSubIsland(grid1, grid2, m, n, i, j, visited)){
                        subIslandsCount++;
                    }
                }
            }
        }

        return subIslandsCount;
    }

    private boolean isSubIsland(int[][] grid1, int[][] grid2, int m, int n, int i, int j, boolean[][] visited)
    {
        visited[i][j] = true;

        // assume current island is a sub-island only if current cell is also land in grid1
        boolean isSubIsland = (grid1[i][j] == 1);

        //explore neighbours
        for(int[] dir : DIRECTIONS){
            int x = dir[0] + i;
            int y = dir[1] + j;

            //boundary check
            if(x < 0 || x >= m || y < 0 || y >= n){
                continue; //out of boundary
            }

            if (grid2[x][y] == 1 && !visited[x][y]) {
                // Continue exploring the entire island. If any recursive call returns false, the whole island is not a sub-island.
                isSubIsland &= isSubIsland(grid1, grid2, m, n, x, y, visited);
            }
        }

        return isSubIsland;
    }
}
