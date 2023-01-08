package matrix;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Coordinates> queue = new LinkedList<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        queue.add(new Coordinates(0, 0, 1));
        visited.add(new Pair<>(0, 0));
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1} };

        while (!queue.isEmpty()){
            Coordinates poll = queue.poll();

            if(poll.row == rows-1 && poll.col == cols-1){
                return poll.path;
            }

            for(int[] dir : directions){
                int x = poll.row + dir[0];
                int y = poll.col + dir[1];

                if(x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] != 1 && !visited.contains(new Pair<>(x, y))){
                    queue.add(new Coordinates(x, y, poll.path+1));
                }
            }
        }

        return -1;
    }
}

class Coordinates{
    int row;
    int col;
    int path;

    public Coordinates(int row, int col, int path) {
        this.row = row;
        this.col = col;
        this.path = path;
    }
}
