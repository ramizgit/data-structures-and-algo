package graph.bfsZeroOne;

import java.util.*;

public class MinCostGridPath {

    //https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/

    /*
    APPROACH : graph + 0-1 bfs + relaxation (not DP remembe)
     */

    public int minCost(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        //cost grid, initialized to max value, to be relaxed later
        int[][] cost = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        //bfs logic
        Deque<CellCost> deque = new ArrayDeque<>();
        deque.offerFirst(new CellCost(0, 0, 0));
        cost[0][0] = 0; //starting cell

        int[][] directions = { {0, 1, 1}, //right
                                {0, -1, 2}, //left
                                {1, 0, 3}, //down
                                {-1, 0, 4} }; //up

        while(!deque.isEmpty()){
            CellCost curr = deque.pollFirst();

            //early exit
            /*
            Early exit is safe here because 0-1 BFS processes nodes in increasing cost order, so this is the minimum cost to reach destination
             */
            if(curr.row == m-1 && curr.col == n-1){
                return curr.cost;
            }

            //explore all four directions
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;
                int directionCode = dir[2];

                if(x>=0 && x<m && y>=0 && y<n){
                    int newCost = curr.cost + (grid[curr.row][curr.col] == directionCode ? 0 : 1);

                    if(newCost < cost[x][y]){
                        cost[x][y] = newCost; //relaxation

                        if(grid[curr.row][curr.col] == directionCode){
                            deque.offerFirst(new CellCost(x, y, newCost));
                        }else{
                            deque.offerLast(new CellCost(x, y, newCost));
                        }
                    }
                }
            }
        }

        return cost[m-1][n-1];
    }

    class CellCost{
        int row;
        int col;
        int cost;

        public CellCost(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }
}
