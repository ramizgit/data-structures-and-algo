package graph;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOrangesBFS {

    public static void main(String[] args)
    {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid)); //4
        
        int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}};
        System.out.println(orangesRotting(grid2)); //-1

        int[][] grid3 = { {0, 2} };
        System.out.println(orangesRotting(grid3)); //0

        int[][] grid4 = { {1,1,1,1}, {1,1,1,1}, {2,1,1,2}, {1,1,1,1}, {1,1,1,1} };
        System.out.println(orangesRotting(grid4)); //3
    }

    public static int orangesRotting(int[][] grid)
    {
        int rows = grid.length;
        int cols = grid[0].length;
        int numOfFreshOrange=0;
        Queue<Coordinates> queue = new LinkedList<>();

        //scan the grid and collect num of fresh oranges, as well as all rotten orange coordinates for bfs
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 1){
                    numOfFreshOrange++;
                }else if (grid[i][j] == 2){
                    queue.add(new Coordinates(i, j, 0));
                }
            }
        }

        if(numOfFreshOrange == 0){
            return 0;
        }
        
        return bfs(queue, numOfFreshOrange, grid, rows, cols);
    }

    public static int bfs(Queue<Coordinates> queue, int numOfFreshOrange, int[][] grid, int rows, int cols)
    {
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        while (!queue.isEmpty()){
            Coordinates poll = queue.poll();

            for(int[] dir : directions){
                int x = poll.row + dir[0];
                int y = poll.col + dir[1];

                if(x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == 1){
                    grid[x][y] = 2;
                    queue.add(new Coordinates(x, y, poll.distance+1));
                    numOfFreshOrange--;
                    if(numOfFreshOrange == 0){
                        return poll.distance+1;
                    }
                }
            }
        }

        return -1;
    }
}

class Coordinates{
    int row;
    int col;
    int distance;

    public Coordinates(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}
