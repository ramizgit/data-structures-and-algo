package meta;

public class DfsIceMeltInGrid {

  //------------------IMPORTANT : THIS QUESTION WAS ASKED IN META INTERVIEW---------------------

    public static void main(String[] args)
    {
        int[][] grid = { {3,1,6,5},
                         {5,7,6,4},
                         {3,4,5,6},
                         {1,2,3,4}};

        System.out.println(exit(grid));
    }

    private static int exit(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i<m; i++){
            for(int j=0; j<n; j++){
                min = Math.min(min, grid[i][j]);
                max = Math.max(max, grid[i][j]);
            }
        }

        System.out.println("min:"+ min);
        System.out.println("max:"+ max);

        int median = 0;

        while (min <= max){
            median = (max + min)/2;
            System.out.println("median:"+ median);

            int[][] grid_copy = new int[m][n];

            //decrease count by median
            for(int i = 0; i<m; i++){
                for(int j=0; j<n; j++){
                    grid_copy[i][j] = grid[i][j] - median;
                }
            }

            if(canExit(grid_copy)){
                //reduce median
                min = median + 1;
            }else{
                //increase median
                max = median - 1;
            }
        }

        return min;
    }

    private static boolean canExit(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        return dfs(grid, m, n, 0, 0, visited);
    }

    private static boolean dfs(int[][] grid, int m, int n, int i, int j, boolean[][] visited)
    {
        if(i == m-1 && j == n-1){
            return true;
        }
        visited[i][j] = true;

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 && !visited[x][y]){
                return dfs(grid, m, n, x, y, visited);
            }
        }
        return false;
    }
}

