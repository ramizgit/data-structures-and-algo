package matrix;

public class LongestIncreasingPathWithDp {

    public static void main(String[] args)
    {
        //strictly increasing
        int[][] grid = { {5,4,3,2},
                         {6,7,8,1},
                         {5,6,7,8},
                         {12,11,10,9} };

        System.out.println(longestPath(grid)); //8

        //strictly increasing
        int[][] grid2 = {{5,4,3,2},
                         {6,7,8,1},
                         {5,6,9,10},
                         {14,13,12,11} }; //14

        System.out.println(longestPath(grid2));

        //strictly increasing
        int[][] grid3 = {{1,2,3,4},
                {8,7,6,5},
                {9,10,11,12},
                {16,15,14,13} }; //16

        System.out.println(longestPath(grid3));
    }

    private static int longestPath(int[][] grid)
    {
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;

        int[][] dp = new int[rows][cols];
        
        //initialize dp to zero
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                dp[i][j] = 0;
            }
        }

        //dfs
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                max = Math.max(max, dfs(grid, rows, cols, i, j, dp));
            }
        }

        //print dp
        //printdpmatrix(dp, rows, cols);

        return max;
    }

    private static int dfs(int[][] grid, int m, int n, int i, int j, int[][] dp)
    {
        //if already calculated, return it
        if(dp[i][j] != 0){
            return dp[i][j];
        }

        int max = 0;

        int[][] directions = { {0,1}, {0,-1}, {1, 0}, {-1,0} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] > grid[i][j]){
                max = Math.max(max, dfs(grid, m, n, x, y, dp));
                dp[x][y] = max;
            }
        }

        return max + 1;
    }

    private static void printdpmatrix(int[][] dp, int rows, int cols)
    {
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                System.out.print(dp[r][c]+":");
            }
            System.out.println();
        }
    }
}
