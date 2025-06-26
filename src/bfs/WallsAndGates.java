package bfs;

import matrix.Coordinates;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    //https://leetcode.com/problems/walls-and-gates/description/
    public static void main(String[] args)
    {
        int MAX = Integer.MAX_VALUE;
        int[][] grid = { {MAX, -1, 0, MAX},
                         {MAX, MAX, MAX, -1},
                         {MAX, -1, MAX, -1},
                         {0, -1, MAX, MAX} };

        fillEmptyRooms(grid);
    }

    private static void fillEmptyRooms(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Coordinates> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        //find all gates and to bfs queue
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0){
                    queue.add(new Coordinates(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        //now run bfs and fill empty rooms
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        while (!queue.isEmpty()){
            Coordinates gate = queue.poll();

            for(int[] dir : directions){
                int x = gate.row + dir[0];
                int y = gate.col + dir[1];

                if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] != -1){
                    grid[x][y] = gate.dist + 1;
                    queue.add(new Coordinates(x, y, grid[x][y]));
                    visited[x][y] = true;
                }
            }
        }
        System.out.println(grid);
    }
}
