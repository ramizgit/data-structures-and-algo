package grid;

import java.util.*;

/*
Problem — Escape Before Flood
You are given a 2D grid containing:

'.' → empty cell
'#' → wall
'S' → starting position
'E' → exit
'W' → water source

Rules:

Water spreads every minute to adjacent cells (up/down/left/right).
Then the player may move one step (up/down/left/right).
Player cannot:
    enter walls
    enter flooded cells
    stay in a cell at or after the minute it floods

Return the minimum number of moves needed to reach the exit.

If impossible, return -1.
 */

public class EscapeBeforeFlood {

    public int minimumMovesToEscape(String[] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length();

        //iterate grid to collect water sources as well as starting point
        int startRow = -1;
        int startCol = -1;
        Queue<Cell> waterBfsQueue = new ArrayDeque<>(); //Queue of {row, col}
        boolean[][] visited = new boolean[m][n]; //needed for multi source visited tracking

        int[][] floodTime = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(floodTime[i], -1);
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char ch = grid[i].charAt(j);

                if(ch == 'S'){
                   startRow = i;
                   startCol = j;
                }else if(ch == 'W'){
                    waterBfsQueue.offer(new Cell(i, j, 0));
                    visited[i][j] = true;
                    floodTime[i][j] = 0;
                }
            }
        }

        //edge case flood reaches immediately
        if(floodTime[startRow][startCol] == 0){
            return -1;
        }

        //multi source bfs to populate flood time of each cell
        int[][] directions = { {0, 1}, //right
                                {0, -1}, //left
                                {1, 0}, //down
                                {-1, 0} }; //up

        while(!waterBfsQueue.isEmpty()){

            Cell currCell = waterBfsQueue.poll();

            //explore neighbours
            for(int[] dir : directions){
                int x = dir[0] + currCell.row;
                int y = dir[1] + currCell.col;

                //boundary check
                if(x < 0 || x >= m || y < 0 || y >= n){
                    continue; //out of boundary
                }

                //wall check
                if(grid[x].charAt(y) == '#'){
                    continue; //cant enter wall
                }

                //visited check
                if(visited[x][y]){
                    continue; //already visited
                }

                waterBfsQueue.offer(new Cell(x, y, currCell.time + 1)); //enqueue
                visited[x][y] = true; //mark visited
                floodTime[x][y] = currCell.time + 1;
            }
        }

        //second bfs to find min steps to reach target

        //bfs queue
        Queue<Cell> playerBfsQueue = new ArrayDeque<>();
        playerBfsQueue.offer(new Cell(startRow, startCol, 0));
        
        visited = new boolean[m][n]; //reset visited
        visited[startRow][startCol] = true; //starting cell

        while(!playerBfsQueue.isEmpty()){
            
            Cell curr = playerBfsQueue.poll();

            //exit condition
            if(grid[curr.row].charAt(curr.col) == 'E'){
                return curr.time;
            }

            //explore neighbours
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                //boundary check
                if(x < 0 || x >= m || y < 0 || y >= n){
                    continue; //out of boundary
                }

                //wall check
                if(grid[x].charAt(y) == '#'){
                    continue; //cant enter wall
                }

                //visited check
                if(visited[x][y]){
                    continue; //already visited
                }

                int arrivalTime = curr.time + 1;

                //flood time constraint
                if(floodTime[x][y] != -1 && arrivalTime >= floodTime[x][y]){
                    continue; //water moves first constraint
                }

                playerBfsQueue.offer(new Cell(x, y, arrivalTime)); //enqueue
                visited[x][y] = true; //mark visited
            }
        }

        return -1;
    }

    class Cell{
        int row;
        int col;
        int time;

        public Cell(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
