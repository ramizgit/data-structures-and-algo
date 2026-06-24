package consistenthashing.grid.bfs;

import java.util.*;

public class AsFarFromLandAsPossible {

    //https://leetcode.com/problems/as-far-from-land-as-possible/

    public int maxDistance(int[][] grid) {

        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        int n = grid.length;

        //approach : multisource bfs from 1's

        //collect all 1s and do multi source bfs to populate dist for 0 cells
        Queue<State> bfsQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n]; //to keep track of visited cells to avoid infinite loop

        //collect all 1 cells
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    bfsQueue.add(new State(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        //edge case if all 0 cells or all 1 cells
        if (bfsQueue.isEmpty() || bfsQueue.size() == n * n) {
            return -1;
        }

        int maxDist = -1;
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} }; //all four directions

        //bfs logic
        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //explore all four directions
            for(int[] dir : directions){

                int newRow = dir[0] + curr.row;
                int newCol = dir[1] + curr.col;

                //boundary check
                if(newRow < 0 || newRow >= n || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                //visited check
                if(visited[newRow][newCol]){
                    continue; //already visited
                }

                visited[newRow][newCol] = true; //mark visited
                bfsQueue.add(new State(newRow, newCol, curr.dist + 1)); //enqueue
                maxDist = Math.max(maxDist, curr.dist + 1); //track max
            }
        }

        return maxDist;
    }

    class State {
        int row;
        int col;
        int dist;

        public State(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}


