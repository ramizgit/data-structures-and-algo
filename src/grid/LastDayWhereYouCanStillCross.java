package grid;

import java.util.*;

public class LastDayWhereYouCanStillCross {

    //https://leetcode.com/problems/last-day-where-you-can-still-cross/description/
    public int latestDayToCross(int row, int col, int[][] cells)
    {
        int low = 0;
        int high = cells.length-1;
        int answer = 0;

        while(low <= high){
            int day = low + (high - low) / 2;

            if(canExit(row, col, cells, day)){
                answer = day; //possible answer
                low = day + 1; //try higher;
            }else{
                high = day - 1; //cant exit, try lower
            }
        }

        return answer;
    }

    public boolean canExit(int row, int col, int[][] cells, int day)
    {
        int[][] grid = new int[row][col]; //initially all cell as 0
        for(int d=0; d<day; d++){
            int[] cell = cells[d];
            grid[cell[0]][cell[1]] = 1; //fill water in the grid
        }

        //add starting points to bfs queue
        Queue<Coordinates> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[row][col]; //to avoid infinite loop

        for(int c=0; c<col; c++){
            if(grid[0][c] == 0){
                queue.add(new Coordinates(0, c));
                visited[0][c] = true;
            }
        }

        int[][] directions = { {1, 0}, {-1, 0}, {0, -1}, {0, 1} };

        while(!queue.isEmpty()){
            Coordinates curr = queue.poll();

            //exit condition
            if(curr.row == row -1){
                return true;
            }

            for(int[] dir : directions){
                int x = curr.row + dir[0];
                int y = curr.col + dir[1];

                if(x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 0 && !visited[x][y]){
                    queue.add(new Coordinates(x, y));
                    visited[x][y] = true;
                }
            }
        }

        return false;
    }
}
