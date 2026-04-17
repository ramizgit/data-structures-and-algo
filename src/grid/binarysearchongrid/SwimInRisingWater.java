package grid.binarysearchongrid;

public class SwimInRisingWater {

    //https://leetcode.com/problems/swim-in-rising-water/description/

    //Time complexity :O(m*n) + O(m * n * log(max - min)) -----> O(m * n * log(max - min))
    public int swimInWater(int[][] grid)
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

            if(canSwim(grid, m, n, mid)){ //O(m*n)
                answer = mid; //possible answer
                high = mid -1; //try lower to minimize time to swim across
            }else{
                low = mid + 1; //try higher
            }

        }

        return answer;
    }

    public boolean canSwim(int[][] grid, int m, int n, int time)
    {
        if (grid[0][0] > time) {
            return false; //early exit if cant swim
        }

        boolean[][] visited = new boolean[m][n];
        return dfs(grid, m, n, time, 0, 0, visited);
    }

    public boolean dfs(int[][] grid, int m, int n, int time, int i, int j, boolean[][] visited)
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

            if(x>=0 && x<m && y>=0 && y<n && !visited[x][y] && grid[x][y] <= time){
                if(dfs(grid, m, n, time, x, y, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}
