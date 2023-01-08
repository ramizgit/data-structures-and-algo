package matrix;

public class NumberOfEnclaveDFS {
    public static void main(String[] args)
    {
        int[][] grid = {{0,0,0,0},
                        {1,0,1,0},
                        {0,1,1,0},
                        {0,0,0,0}};
        System.out.println(numEnclaves(grid)); //3
    }

    public static int numEnclaves(int[][] grid)
    {
        int numOfEnclaves = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for(int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++){
                if( onTheBorder(rows, cols, i, j) && (grid[i][j] == 1) ) {
                    dfs(grid, rows, cols, i, j);
                }
            }
        }

        for(int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++){
                if( grid[i][j] == 1 ) {
                    numOfEnclaves++;
                }
            }
        }

        return numOfEnclaves;
    }

    private static void dfs(int[][] grid, int rows, int cols, int i, int j)
    {
        grid[i][j] = 2;

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x>=0 && y>=0 && x<rows && y<cols && grid[x][y] == 1){
                dfs(grid, rows, cols, x, y);
            }
        }
    }

    private static boolean onTheBorder(int rows, int cols, int i, int j)
    {
        return ((i ==0 || i == rows -1) || (j ==0 || j == cols-1));
    }
}
