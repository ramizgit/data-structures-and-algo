package graph.bfsZeroOne;

import java.util.*;

public class MinCostGridPath {

    //https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/

    /*
    APPROACH : graph + 0-1 bfs + relaxation (not DP remember)
     */

    public int minCost(int[][] grid)
    {
        //input validation
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        //bfs logic
        Deque<State> bfsDeque = new ArrayDeque<>();
        bfsDeque.offerFirst(new State(0, 0, 0)); //starting cell

        //cost grid, initialized to max value, to be relaxed later
        int[][] cost = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[0][0] = 0; //starting cell with zero cost

        int[][] directions = { {0, 1, 1}, //right
                                {0, -1, 2}, //left
                                {1, 0, 3}, //down
                                {-1, 0, 4} }; //up

        while(!bfsDeque.isEmpty()){

            State curr = bfsDeque.pollFirst();

            //ignore stale/outdated states
            if (curr.cost > cost[curr.row][curr.col]) {
                continue;
            }

            //early exit
            /*
            Early exit is safe here because 0-1 BFS processes nodes in increasing cost order, so this is the minimum cost to reach destination
             */
            if(curr.row == m-1 && curr.col == n-1){
                return curr.cost;
            }

            //explore all four directions
            for(int[] dir : directions){

                int newRow = dir[0] + curr.row;
                int newCol = dir[1] + curr.col;
                int directionCode = dir[2];

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                int edgeCost = (grid[curr.row][curr.col] == directionCode) ? 0 : 1;
                int newCost = curr.cost + edgeCost;

                if(newCost < cost[newRow][newCol]){
                    //relaxation
                    cost[newRow][newCol] = newCost;

                    //0-1 bfs
                    if(edgeCost == 0){
                        bfsDeque.offerFirst(new State(newRow, newCol, newCost));
                    }else{
                        bfsDeque.offerLast(new State(newRow, newCol, newCost));
                    }
                }
            }
        }

        return cost[m-1][n-1];
    }

    class State {
        int row;
        int col;
        int cost;

        public State(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }
}
