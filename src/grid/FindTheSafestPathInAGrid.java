package grid;

import java.util.*;

public class FindTheSafestPathInAGrid {

    public int maximumSafenessFactor(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        //step1 : iterate grid and collect all thief cells in queue for bfs
        int[][] safe = new int[m][n];
        Queue<Coordinates> queue = new ArrayDeque<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                safe[i][j] = -1; //initally mark all cells -1, relax it later while bfs

                if(grid[i][j] == 1){
                    safe[i][j] = 0; //manhattan dist is 0 for the thief cells itself
                    queue.add(new Coordinates(i, j, 0)); //add to queue for bfs logic
                }
            }
        }

        //step2 : start bfs from thief cells and populate safeness factor for each other cells
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        int low = 0;
        int high = 0;

        while(!queue.isEmpty()){
            Coordinates thief = queue.poll();

            for(int[] dir : directions){
                int x = dir[0] + thief.row;
                int y = dir[1] + thief.col;

                if(x >= 0 && x < m && y >= 0 && y < n && safe[x][y] == -1){
                    safe[x][y] = thief.dist + 1; //relaxation
                    queue.add(new Coordinates(x, y, safe[x][y]));

                    high = Math.max(high, safe[x][y]); //for binary search high
                }
            }
        }

        //step3 : find path from (0,0) to (m-1,n-1)
        //we could use binary sarch
        int safenessFactor = 0;
        while(low <= high){
            int mid = low + (high - low) / 2;

            if(canExit(safe, mid)){
                safenessFactor = mid; //possible answer
                low = mid + 1; //try higher
            }else{
                high = mid - 1;
            }
        }

        return safenessFactor;
    }

    public boolean canExit(int[][] safe, int factor)
    {
        if(safe[0][0] < factor){
            return false;
        }

        int m = safe.length;
        int n = safe[0].length;
        boolean[][] visited = new boolean[m][n];

        return dfs(safe, factor, m, n, 0, 0, visited); //todo:can use bfs here instead of dfs
    }

    //dfs
    public boolean dfs(int[][] safe, int factor, int m, int n, int i, int j, boolean[][] visited)
    {
        if (i == m - 1 && j == n - 1) {
            return true;
        }

        visited[i][j] = true;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int x = dir[0] + i;
            int y = dir[1] + j;

            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && safe[x][y] >= factor) {
                if (dfs(safe, factor, m, n, x, y, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}

/*
class Coordinates{
    int row;
    int col;
    int dist;

    public Coordinates(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
 */
