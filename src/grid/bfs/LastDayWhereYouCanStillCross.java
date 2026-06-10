package consistenthashing.grid.bfs;

import java.util.*;

public class LastDayWhereYouCanStillCross {

    //https://leetcode.com/problems/last-day-where-you-can-still-cross/description/
    
    public int latestDayToCross(int row, int col, int[][] cells)
    {
        //binary search on cells array
        int low = 0; //lowest cells index
        int high = cells.length-1; //highest cells index
        int answer = 0;

        while(low <= high){

            int mid = low + (high - low) / 2;

            if(canReachTarget(row, col, cells, mid)){
                answer = mid; //possible answer
                low = mid + 1; //try higher
            }else{
                high = mid - 1; //cant exit, try lower
            }
        }

        return answer;
    }

    public boolean canReachTarget(int row, int col, int[][] cells, int day)
    {
        int[][] grid = new int[row][col]; //initially all cell as 0

        //fill water in the grid
        for(int d=0; d<day; d++){
            int[] cell = cells[d];
            grid[cell[0]][cell[1]] = 1;
        }

        //note : use bfs as we have multiple entry points, all cells in the first row where its 0
        //add starting points to bfs queue
        Queue<int[]> bfsQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[row][col]; //to avoid infinite loop

        for(int c=0; c<col; c++){
            if(grid[0][c] == 0){
                bfsQueue.offer(new int[]{0, c});
                visited[0][c] = true;
            }
        }

        int[][] directions = { {1, 0}, {-1, 0}, {0, -1}, {0, 1} };

        while(!bfsQueue.isEmpty()){

            int[] curr = bfsQueue.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            //exit condition
            if(currRow == row -1){
                return true;
            }

            //explore neighbours
            for(int[] dir : directions){
                int x = currRow + dir[0];
                int y = currCol + dir[1];

                if(x >= 0 && x < row && y >= 0 && y < col && //boundary check
                        !visited[x][y] && //visited check
                        grid[x][y] == 0 ){ //constraint check

                    bfsQueue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }

        return false;
    }
}
