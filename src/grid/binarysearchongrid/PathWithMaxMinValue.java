package grid.binarysearchongrid;

public class PathWithMaxMinValue {

    //https://leetcode.com/problems/path-with-maximum-minimum-value/description/

    /*
    Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0)
    and ending at (m - 1, n - 1) moving in the 4 cardinal directions.
    The score of a path is the minimum value in that path
     */

    //Time complexity :O(m*n) + O(m * n * log(max - min)) -----> O(m * n * log(max - min))
    public int maximumMinimumPath(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<m; i++){ //O(m*n)
            for(int j=0; j<n; j++){
                min = Math.min(min, grid[i][j]);
                max = Math.max(max, grid[i][j]);
            }
        }

        //binary search logic
        int low = min;
        int high = max;
        int answer = 0;

        while(low <= high){ //O(log(max - min))
            int mid = low + (high - low) / 2;

            if(pathExists(grid, m, n, mid)){ //O(m*n)
                answer = mid; //possible answer
                low = mid + 1; //try higher to maximise score
            }else{
                high = mid -1; //try lower
            }

        }

        return answer;
    }

    public boolean pathExists(int[][] grid, int m, int n, int score)
    {
        if (grid[0][0] < score) {
            return false; //early exit if no path exists
        }

        boolean[][] visited = new boolean[m][n];
        return dfs(grid, m, n, score, 0, 0, visited);
    }

    public boolean dfs(int[][] grid, int m, int n, int score, int i, int j, boolean[][] visited)
    {
        if(i == m-1 && j == n-1){
            return true;
        }

        visited[i][j] = true;

        //explore all possible directions
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x>=0 && x<m && y>=0 && y<n && !visited[x][y] && grid[x][y] >= score){
                if(dfs(grid, m, n, score, x, y, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}
