package matrix;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMatrixBFS {
    public static void main(String[] args)
    {
        int[][] grid = { {0, 1}, {1, 0} };
        System.out.println(shortestPathBinaryMatrix(grid)); //2

        int[][] grid2 = { {0,0,0},{1,1,0},{1,1,0} };
        System.out.println(shortestPathBinaryMatrix(grid2)); //4

        int[][] grid3 = {{1,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestPathBinaryMatrix(grid3)); //-1
    }

    public static int shortestPathBinaryMatrix(int[][] grid)
    {
        if(grid == null || grid[0][0] != 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        Queue<Coordinates> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.add(new Coordinates(0, 0, 1));
        visited[0][0] = true;
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1} };

        while (!queue.isEmpty()){
            Coordinates poll = queue.poll();

            if(poll.row == m-1 && poll.col == n-1){
                return poll.dist;
            }

            for(int[] dir : directions){
                int x = poll.row + dir[0];
                int y = poll.col + dir[1];

                if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] != 1 && !visited[x][y]){
                    queue.add(new Coordinates(x, y, poll.dist+1));
                    visited[x][y] = true;
                }
            }
        }

        return -1;
    }
}

/*class Coordinates {
    int row;
    int col;
    int dist;

    public Coordinates(int row, int col, int dist){
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}*/
