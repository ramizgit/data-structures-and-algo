package consistenthashing.grid.binarysearchongrid;

import java.util.*;

public class PathWithMinimumEffortDijkstra {

    //https://leetcode.com/problems/path-with-minimum-effort/description/

    /*
    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     */

    private static final int[][] DIRECTIONS = {
            {0, 1}, //right
            {0, -1}, //left
            {1, 0}, //down
            {-1, 0} }; //up

    public int minimumEffortPath(int[][] heights)
    {
        //input validation
        if(heights == null || heights.length == 0){
            return -1; //?
        }

        int m = heights.length;
        int n = heights[0].length;

        PriorityQueue<State> minheap = new PriorityQueue<>( (a, b) -> a.effort - b.effort );
        minheap.offer(new State(0, 0, 0)); //starting position with zero effort

        //cost array
        int[][] cost = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[0][0] = 0; //starting position with zero effort

        while(!minheap.isEmpty()){

            State curr = minheap.poll();

            //staleness check
            if(curr.effort > cost[curr.row][curr.col]){
                continue;
            }

            //exit condition
            if(curr.row == m-1 && curr.col == n-1){
                return curr.effort;
            }

            //explore neighbours and do relaxation
            for(int[] dir : DIRECTIONS){
                int newRow = dir[0] + curr.row;
                int newCol = dir[1] + curr.col;

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                int edgeDiff = Math.abs(heights[curr.row][curr.col] - heights[newRow][newCol]);

                //curr.effort = maximum effort seen so far on the path = maximum edge difference encountered on the path
                int newEffort = Math.max(curr.effort, edgeDiff);

                if(newEffort < cost[newRow][newCol]){
                    //relaxation
                    cost[newRow][newCol] = newEffort;
                    minheap.offer(new State(newRow, newCol, newEffort));
                }
            }
        }

        return -1;
    }

    class State{
        int row;
        int col;
        int effort; //maximum effort seen so far on the path

        public State(int row, int col, int effort) {
            this.row = row;
            this.col = col;
            this.effort = effort;
        }
    }
}
