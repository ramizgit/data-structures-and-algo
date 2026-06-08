package consistenthashing.grid.binarysearchongrid;

import java.util.*;

public class SwimInRisingWaterBinarySearch {

    //https://leetcode.com/problems/swim-in-rising-water/description/
    //Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

    private static final int[][] DIRECTIONS = {
            {0, 1}, //right
            {0, -1}, //left
            {1, 0}, //down
            {-1, 0} }; //up

    public int swimInWater(int[][] grid)
    {
        int n = grid.length;

        int max = Integer.MIN_VALUE;

        for(int i=0; i<n; i++){ //O(n*n)
            for(int j=0; j<n; j++){
                max = Math.max(max, grid[i][j]);
            }
        }

        //binary search logic
        int low = Math.max(grid[0][0], grid[n-1][n-1]); // answer cannot be smaller than start/destination elevation
        int high = max; // by this time all cells are submerged, so a path is guaranteed to exist
        int answer = 0;

        while(low <= high){ //O(log(high - low))
            int mid = low + (high - low) / 2;

            if(canReachTarget(grid, n, mid)){ //O(n*n)
                answer = mid; //possible answer
                high = mid -1; //try lower to minimize time to swim across
            }else{
                low = mid + 1; //try higher
            }
        }

        return answer;
    }

    public boolean canReachTarget(int[][] grid, int n, int time)
    {
        Queue<int[]> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new int[]{0, 0}); //starting position

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true; //starting position

        while(!bfsQueue.isEmpty()){

            int[] curr = bfsQueue.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            //exit condition
            if(currRow == n-1 && currCol == n-1){
                return true;
            }

            //explore neighbours
            for(int[] dir : DIRECTIONS){
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];

                if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && //boundary check
                                !visited[newRow][newCol] && //visited check
                                grid[newRow][newCol] <= time){ //constraint check
                    bfsQueue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return false;
    }
}

/*
just a note, why plan DP won't work here.
in this grid, we can go all four directions, hence we can revisit nodes, hence CYCLE exists. DP breaks for circular dependency.
if movements were restricted only to right + down, grid becomes DAG, DP works
 */
