package consistenthashing.grid.bfs;

import java.util.*;

public class RottenOrangesBFS {

    public int orangesRotting(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int numOfFreshOrange=0;
        Queue<State> bfsQueue = new ArrayDeque<>();

        //scan the grid and collect num of fresh oranges, as well as all rotten orange coordinates for bfs
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    numOfFreshOrange++;
                }else if (grid[i][j] == 2){
                    bfsQueue.add(new State(i, j, 0));
                }
            }
        }

        //edge case
        if(numOfFreshOrange == 0){
            return 0;
        }

        //bfs logic
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        while (!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //explore all four directions
            for(int[] dir : directions){
                int x = curr.row + dir[0];
                int y = curr.col + dir[1];

                if(x >= 0 && x < m && y >= 0  && y < n && grid[x][y] == 1){
                    grid[x][y] = 2; //mark orange as rotten
                    bfsQueue.add(new State(x, y, curr.minutes +1)); //add to bfs queue
                    numOfFreshOrange--;

                    //exit condition
                    if(numOfFreshOrange == 0){
                        return curr.minutes +1;
                    }
                }
            }
        }

        return -1;
    }

    class State {
        int row;
        int col;
        int minutes;

        public State(int row, int col, int minutes) {
            this.row = row;
            this.col = col;
            this.minutes = minutes;
        }
    }
}


