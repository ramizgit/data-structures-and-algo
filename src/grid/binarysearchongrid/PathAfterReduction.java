package grid;

public class PathAfterReduction {
    /*
    problem asked in meta interview
     */

    private static final int[][] DIRECTIONS = {
            {0, 1}, //right
            {0, -1}, //left
            {1, 0}, //down
            {-1, 0} }; //up

    public int maxReduction(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        int max = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }

        int low = 0;
        int high = max;
        int answer = 0;

        while(low <= high){

            int mid = low + (high - low)/2;

            if(canExit(grid, m, n, mid)){
                answer = mid; //possible answer
                low = mid + 1; //try higher
            }else{
                high = mid - 1; //try lower
            }
        }

        return answer;
    }

    private boolean canExit(int[][] grid, int m, int n, int k)
    {
        if (grid[0][0] <= k || grid[m-1][n-1] <= k) { // cannot start or finish
            return false;
        }

        boolean[][] visited = new boolean[m][n];

        return dfs(grid, m, n, 0, 0, visited, k);
    }

    //todo:prefer bfs over dfs due to risk of stack overflow
    //dfs
    private boolean dfs(int[][] grid, int m, int n, int i, int j, boolean[][] visited, int k)
    {
        if(i == m-1 && j == n-1){
            return true;
        }

        visited[i][j] = true;

        for(int[] dir : DIRECTIONS){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && //boundary check
                    !visited[x][y] && //visited check
                    grid[x][y] > k){ //constraint check

                if(dfs(grid, m, n, x, y, visited, k)){
                    return true;
                }
            }
        }

        return false;
    }
}
