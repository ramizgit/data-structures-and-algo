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
        int m = grid.length;
        int n = grid[0].length;

        //mininum num of steps to go from (0,0) to (m-1, n-1) is = (m-1) + (n-1) = m + n -2
        //If I can eliminate at least as many obstacles as the shortest path length then I don’t care about obstacles at all.
        if (k >= m + n - 2) return m + n - 2;

        Queue<State> queue = new ArrayDeque<>(); //bfs queue
        queue.add(new State(0, 0, k, 0));

        /*
        For intermediate cells, we care about future possibilities, hence need to track k as well in the visited state
         */
        boolean[][][] visited = new boolean[m][n][k+1]; //keep track of visited cells
        visited[0][0][k] = true;

        while(!queue.isEmpty()){
            State curr = queue.poll();

            //early exit
            /*
            we don’t care about what k is left, we only care what is the minimum distance to reach here
             */
            if(curr.row == m-1 && curr.col == n-1){
                return curr.dist;
            }

            //explore all four directions
            int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                if(x>=0 && x<m && y>=0 && y<n){
                    int remainingK = curr.k - grid[x][y];
                    if (remainingK >= 0 && !visited[x][y][remainingK]) {
                        visited[x][y][remainingK] = true;
                        queue.offer(new State(x, y, remainingK, curr.dist + 1));
                    }
                }
            }
        }

        return -1;
    }

    class State{
        int row;
        int col;
        int k;
        int dist;

        public State(int row, int col, int k, int dist) {
            this.row = row;
            this.col = col;
            this.k = k;
            this.dist = dist;
        }
    }
}


