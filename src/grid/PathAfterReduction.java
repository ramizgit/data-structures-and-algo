package grid;

public class PathAfterReduction {
    public static void main(String[] args)
    {
        int[][] grid = {
                {5, 4, 3},
                {6, 1, 2},
                {7, 8, 9}
        };
        System.out.println(maxReduction(grid)); //4

        int[][] grid2 = {
                {3, 2},
                {1, 4}
        };
        System.out.println(maxReduction(grid2)); //1
    }

    private static int maxReduction(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        int left = 0;
        int right = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                right = Math.max(right, grid[i][j]);
            }
        }

        int max = 0;

        while(left <= right){
            int mid = left + (right - left)/2;

            if (grid[0][0] - mid <= 0) { // cannot start
                right = mid - 1;
                continue;
            }

            boolean[][] visited = new boolean[m][n];

            if(canExit(grid, m, n, 0, 0, visited, mid)){
                max = mid; //possible answer
                left = mid + 1; //try harder, i mean with higher number
            }else{
                right = mid - 1;
            }
        }

        return max;
    }

    //dfs
    private static boolean canExit(int[][] grid, int m, int n, int i, int j, boolean[][] visited, int k)
    {
        visited[i][j] = true;

        if(i == m-1 && j == n-1){
            return true;
        }

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && !visited[x][y] && grid[x][y] - k > 0){
                if(canExit(grid, m, n, x, y, visited, k)){
                    return true;
                }
            }
        }

        return false;
    }
}
