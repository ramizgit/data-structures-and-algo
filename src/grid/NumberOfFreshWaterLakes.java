package grid;

import java.util.*;

/*
Here 0 indidates water and its value can be either saltwater or freshwater
1 is land
the input matrix is implicitly surrounded by an infinite ocean as shown in the comments part in the first and second row.
Here the condition is water moves vertically and horizontally (up down left right) but not diagonally
*/

public class NumberOfFreshWaterLakes {

    int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    public int numOfFreshWaterLakes(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;
        //int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {1, -1} };

        //start with boundary cells do multi source bfs from 0 cells (salt water cells) and mark them visited
        Queue<Cell> saltWaterQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(onTheBorder(i, j, m, n) && grid[i][j] == 0){
                    saltWaterQueue.offer(new Cell(i, j));
                    visited[i][j] = true;
                }
            }
        }

        //mark all connected salt water cells as -1
        while(!saltWaterQueue.isEmpty()){
            Cell curr =  saltWaterQueue.poll();

            //explore neighbours
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                if(x>=0 && x<m && y>=0 && y<n && grid[x][y] == 0 && !visited[x][y]){
                    visited[x][y] = true;
                    saltWaterQueue.offer(new Cell(x, y));
                }
            }
        }

        //now run dfs on the grid and count islands with unvisited 0 cells (fresh water cells)
        int numLakes = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0 && !visited[i][j]){
                    numLakes++;
                    dfs(grid, m, n, i, j, visited);
                }
            }
        }

        return numLakes;
    }

    private void dfs(int[][] grid, int m, int n, int i, int j, boolean[][] visited)
    {
        visited[i][j] = true;

        //explore neighbours
        for(int[] dir : directions){
            int x = dir[0] + i;
            int y = dir[1] + j;

            if(x>=0 && x<m && y>=0 && y<n && grid[x][y] == 0 && !visited[x][y]){
                dfs(grid, m, n, x, y, visited);
            }
        }
    }

    private boolean onTheBorder(int i, int j, int m, int n)
    {
        return i == 0 || i == m-1 || j == 0 || j == n-1;
    }

    class Cell{
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
