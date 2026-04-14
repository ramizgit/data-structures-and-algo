package graph.bfsZeroOne;

import java.util.*;

public class MinimumObstacleRemovalToReachCorner {

    //https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/description/

    /*
    APPROACH : graph + 0-1 bfs + relaxation (not DP remembe)
     */

    public int minimumObstacles(int[][] grid)
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

        //bfs
        Deque<Cellcost> deque = new ArrayDeque<>();
        deque.offerFirst(new Cellcost(0, 0, 0));
        cost[0][0] = 0;
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        while(!deque.isEmpty()){
            Cellcost curr = deque.pollFirst();

            //early exit
            if(curr.row == m-1 && curr.col == n-1){
                return curr.cost;
            }

            //explore four directions
            for(int[] dir : directions){
                int x = curr.row + dir[0];
                int y = curr.col + dir[1];

                if(x>=0 && x<m && y>=0 && y<n){
                    int newCost = curr.cost + grid[x][y]; // 0 or 1
                    if(newCost < cost[x][y]){
                        cost[x][y] = newCost; //relaxation

                        if(grid[x][y] == 0){
                            deque.offerFirst(new Cellcost(x, y, newCost)); //cost 0
                        }else{
                            deque.offerLast(new Cellcost(x, y, newCost)); //cost 1
                        }
                    }
                }
            }

        }

        return cost[m-1][n-1]; //should not reach here ideally, will early exit
    }
}

class Cellcost{
    int row;
    int col;
    int cost;

    public Cellcost(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }
}
