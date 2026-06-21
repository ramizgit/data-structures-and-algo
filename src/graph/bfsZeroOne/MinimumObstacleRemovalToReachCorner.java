package graph.bfsZeroOne;

import java.util.*;

public class MinimumObstacleRemovalToReachCorner {

    //https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/description/

    /*
    APPROACH : graph + 0-1 bfs + relaxation
    0-1 BFS is essentially a special case of Dijkstra. Hence we maintain minimum cost to cost[][] reach each node
    and do relaxation.
     */

    public int minimumObstacles(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        Deque<State> bfsDeque = new ArrayDeque<>();
        bfsDeque.offerFirst(new State(0, 0, 0)); //starting cell with 0 cost

        //cost grid, initialized to max value, to be relaxed later
        int[][] cost = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[0][0] = 0; //starting cell with 0 cost

        int[][] directions = {
                {0, 1}, //right
                {0, -1}, //left
                {1, 0}, //down
                {-1, 0} //up
        };

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

            //explore four directions
            for(int[] dir : directions){

                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                int newCost = curr.cost + grid[newRow][newCol]; // 0 or 1

                if(newCost < cost[newRow][newCol]){
                    //relaxation
                    cost[newRow][newCol] = newCost;

                    //0-1 bfs core logic
                    if(grid[newRow][newCol] == 0){
                        bfsDeque.offerFirst(new State(newRow, newCol, newCost)); //cost 0
                    }else{
                        bfsDeque.offerLast(new State(newRow, newCol, newCost)); //cost 1
                    }
                }
            }
        }

        return cost[m-1][n-1]; //should not reach here ideally, will early exit
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
