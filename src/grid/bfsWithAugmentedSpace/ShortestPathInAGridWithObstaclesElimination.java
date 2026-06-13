package grid.bfsWithAugmentedSpace;

import java.util.*;

public class ShortestPathInAGridWithObstaclesElimination {

    //https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/description/

    /*
    This is NOT 0-1 BFS as every move (up/down/left/right) costs exactly 1 step, irrespective of it being 0 cell or 1 cell.
    Obstacle is not a cost, its a state, its a limited resource (k budget)
     */
    
    /*
    The general idea is:
    State = position + extra information
    Whenever future decisions depend on “history”, position alone is insufficient.
    So instead of:(row, col)
    you track: (row, col, somethingElse)
     */

    public int shortestPath(int[][] grid, int k)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        //mininum num of steps to go from (0,0) to (m-1, n-1) is = (m-1) + (n-1) = m + n -2
        //If I can eliminate at least as many obstacles as the shortest path length then I don’t care about obstacles at all.
        if (k >= m + n - 2) return m + n - 2;

        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new State(0, 0, k, 0)); //starting point

        /*
        For intermediate cells, we care about future possibilities, hence need to track k as well in the visited state
         */
        //visited[row][col][k]
        //important note : in Dijkstra/BFS, the PQ/BFS state and dist/visited state must represent the SAME state space.
        boolean[][][] visited = new boolean[m][n][k+1]; //keep track of visited cells
        visited[0][0][k] = true;

        //four directions
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //early exit
            /*
            we don’t care about what k is left, we only care what is the minimum distance to reach here
             */
            if(curr.row == m-1 && curr.col == n-1){
                return curr.dist;
            }

            //explore all four directions
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                //boundary check
                if(x < 0 || x >= m || y < 0 || y >= n){
                    continue; //out of boundary
                }

                int newRemainingK = curr.remainingK - grid[x][y]; //grid[x][y] is either 0 or 1 (obstacle)

                if (newRemainingK >= 0 && !visited[x][y][newRemainingK]) {
                    visited[x][y][newRemainingK] = true; //save state for visited tracking
                    bfsQueue.offer(new State(x, y, newRemainingK, curr.dist + 1)); //enqueue
                }
            }
        }

        return -1;
    }

    class State{
        int row;
        int col;
        int remainingK;
        int dist;

        public State(int row, int col, int remainingK, int dist) {
            this.row = row;
            this.col = col;
            this.remainingK = remainingK;
            this.dist = dist;
        }
    }
}


