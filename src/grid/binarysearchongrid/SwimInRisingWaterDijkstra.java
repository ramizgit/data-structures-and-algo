package consistenthashing.grid.binarysearchongrid;

import java.util.*;

public class SwimInRisingWaterDijkstra {

    //https://leetcode.com/problems/swim-in-rising-water/description/
    //Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

    private static final int[][] DIRECTIONS = {
            {0, 1}, //right
            {0, -1}, //left
            {1, 0}, //down
            {-1, 0} }; //up

    // Time:  O(n² log(n²))
    // Space: O(n²)
    public int swimInWater(int[][] grid)
    {
        int n = grid.length;

        //dijskstra algo
        PriorityQueue<State> minheap = new PriorityQueue<>( (a, b) -> a.minTime - b.minTime );//minheap to always fetch mintime record
        minheap.offer(new State(0, 0, grid[0][0])); //starting position

        int[][] cost = new int[n][n]; //cost array to track mintime for each cell
        for(int i=0; i<n; i++){
            Arrays.fill(cost[i], Integer.MAX_VALUE); //start with max possible value
        }
        cost[0][0] = grid[0][0]; //starting position

        while(!minheap.isEmpty()){

            State curr = minheap.poll();

            //staleness check
            if(curr.minTime > cost[curr.row][curr.col]){
                continue;
            }

            //exit condition
            if(curr.row == n-1 && curr.col == n-1){
                return curr.minTime;
            }

            //explore neighbours and relaxation
            for(int[] dir : DIRECTIONS){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //boundary condition
                if(newRow < 0 || newRow >= n || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                int newTime = Math.max(curr.minTime, grid[newRow][newCol]);

                if(newTime < cost[newRow][newCol]){
                    //relaxation
                    cost[newRow][newCol] = newTime;
                    minheap.offer(new State(newRow, newCol, newTime));
                }
            }
        }

        return -1; //should not reach here
    }

    class State{
        int row;
        int col;
        int minTime;

        public State(int row, int col, int minTime) {
            this.row = row;
            this.col = col;
            this.minTime = minTime;
        }
    }
}

/*
just a note, why plan DP won't work here.
in this grid, we can go all four directions, hence we can revisit nodes, hence CYCLE exists. DP breaks for circular dependency.
if movements were restricted only to right + down, grid becomes DAG, DP works
 */
