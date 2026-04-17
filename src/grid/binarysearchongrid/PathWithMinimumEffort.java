package grid;

import java.util.*;

public class PathWithMinimumEffort {

    //https://leetcode.com/problems/path-with-minimum-effort/description/

    /*
    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     */

    //Time complexity = O(m * n * log(maxHeight))
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

        while(low <= high){ //O(log(maxHeight))
            int mid = low + (high - low) / 2;

            if(canTravel(heights, m, n, mid)){
                answer = mid; //possible answer
                high = mid - 1; //try lower to minimize
            }else{
                low = mid + 1; //try higher
            }
        }

        return answer;
    }

    public boolean canTravel(int[][] heights, int m, int n, int effort)
    {
        boolean[][] visited = new boolean[m][n];
        return dfs(heights, m, n, effort, 0, 0, visited);
    }

    //dfs - O(m*n)
    public boolean dfs(int[][] heights, int m, int n, int effort, int i, int j, boolean[][] visited)
    {
        if(i == m-1 && j == n-1){
            return true;
        }

        visited[i][j] = true;

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        //explore all four directions
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x>=0 && x<m && y>=0 && y<n && !visited[x][y] && Math.abs(heights[i][j] - heights[x][y]) <= effort){
                if(dfs(heights, m, n, effort, x, y, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}
