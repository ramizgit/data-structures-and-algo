package microsoft;

import java.util.*;

public class RatInMaze {

    public List<List<Integer>> maze(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        if(grid[0][0] == 0){
            return new ArrayList<>();
        }

        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        curr.add(grid[0][0]);
        boolean[][] visited = new boolean[m][n];
        dfs(grid, m, n, 0, 0, paths, curr, visited);
        return paths;
    }

    private void dfs(int[][] grid, int m, int n, int i, int j, List<List<Integer>> paths, List<Integer> curr, boolean[][] visited)
    {
        //exit condition
        if(i == m-1 && j == n-1){
            //add to final result
            paths.add(new ArrayList<>(curr));
            return;
        }

        visited[i][j] = true;

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x>=0 && x<m && y>=0 && y<n && !visited[x][y] && grid[x][y] != 0){
                curr.add(grid[x][y]);
                dfs(grid, m, n, x, y, paths, curr, visited);
                curr.removeLast(); //backtrack
            }
        }

        visited[i][j] = false; // backtrack
    }
}
