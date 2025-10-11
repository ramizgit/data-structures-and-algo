package grid;

public class NumberOfClosedIslands {
    //https://leetcode.com/problems/number-of-closed-islands/description/
    public static void main(String[] args)
    {
        int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        System.out.println(numOfClosedIslands(grid));
    }

    private static int numOfClosedIslands(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        //start with lands on the border and mark them visited
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(onTheBorder(i, j, m, n) && grid[i][j] == 0){
                    dfs(grid, visited, m, n, i, j);
                }
            }
        }

        //now count num of islands
        int num = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0 && !visited[i][j]){
                    num++;
                    dfs(grid, visited, m, n, i, j);
                }
            }
        }

        return num;
    }

    //dfs
    private static void dfs(int[][] grid, boolean[][] visited, int m, int n, int i, int j)
    {
        visited[i][j] = true;

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && !visited[x][y] && grid[x][y] == 0){
                dfs(grid, visited, m, n, x, y);
            }
        }
    }

    private static boolean onTheBorder(int i, int j, int m, int n){
        return (i == 0 || i == m-1 || j == 0 || j == n-1);
    }

}


