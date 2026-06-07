package grid;

import java.util.*;

public class PathWithMinimumEffortBinarySearch {

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

    //Time complexity = O(m * n * log(max - min))
    public int minimumEffortPath(int[][] heights)
    {
        int m = heights.length;
        int n = heights[0].length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<m; i++){ //O(m*n)
            for(int j=0; j<n; j++){
                min = Math.min(min, heights[i][j]);
                max = Math.max(max, heights[i][j]);
            }
        }

        int low = 0;
        int high = max - min; //max possible absolute diff. between two adj. cells
        int answer = 0;

        while(low <= high){ //O(log(high - low))
            int mid = low + (high - low) / 2;

            if(canReachTarget(heights, m, n, mid)){
                answer = mid; //possible answer
                high = mid - 1; //try lower to minimize
            }else{
                low = mid + 1; //try higher
            }
        }

        return answer;
    }

    //BFS avoids the stack overflow risk that recursive DFS can have on large grids
    private boolean canReachTarget(int[][] heights, int m, int n, int maxEffort)
    {
        Queue<int[]> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new int[]{0, 0}); //starting position

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true; //starting position


        while(!bfsQueue.isEmpty()){
            int[] curr = bfsQueue.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            //exit condition
            if(currRow == m-1 && currCol == n-1){
                return true; //target reached
            }

            //explore neighbours
            for(int[] dir : DIRECTIONS){
                int newRow = dir[0] + currRow;
                int newCol = dir[1] + currCol;

                if(newRow>=0 && newRow<m && newCol>=0 && newCol<n && //boundary check
                        !visited[newRow][newCol] && //visited check
                        Math.abs(heights[currRow][currCol] - heights[newRow][newCol]) <= maxEffort){ //constraint check

                    bfsQueue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return false;
    }

    //DFS
    private boolean canTravel(int[][] heights, int m, int n, int maxEffort)
    {
        boolean[][] visited = new boolean[m][n];
        return dfs(heights, m, n, maxEffort, 0, 0, visited);
    }

    //dfs - O(m*n)
    private boolean dfs(int[][] heights, int m, int n, int maxEffort, int i, int j, boolean[][] visited)
    {
        if(i == m-1 && j == n-1){
            return true;
        }

        visited[i][j] = true;

        //explore all four directions
        for(int[] dir : DIRECTIONS){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x>=0 && x<m && y>=0 && y<n && !visited[x][y] && Math.abs(heights[i][j] - heights[x][y]) <= maxEffort){
                if(dfs(heights, m, n, maxEffort, x, y, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}
