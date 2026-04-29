package grid;

import java.util.*;

public class ShortestPathInAGridWithObstaclesElimination {

    //https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/description/

    /*
    This is NOT 0-1 BFS as every move (up/down/left/right) costs exactly 1 step, irrespective of it being 0 cell or 1 cell.
    Obstacle is not a cost, It is a limited resource (k budget)

     */
    public int shortestPath(int[][] grid, int k)
    {
        int m = grid.length;
        int n = grid[0].length;

        //mininum num of steps to go from (0,0) to (m-1, n-1) is = (m-1) + (n-1) = m + n -2
        //If I can eliminate at least as many obstacles as the shortest path length then I don’t care about obstacles at all.
        if (k >= m + n - 2) return m + n - 2;

        Queue<Coordinate> queue = new ArrayDeque<>(); //bfs queue
        queue.add(new Coordinate(0, 0, k, 0));

        /*
        For intermediate cells, we care about future possibilities, hence need to track k as well in the visited state
         */
        boolean[][][] visited = new boolean[m][n][k+1]; //keep track of visited cells
        visited[0][0][k] = true;

        while(!queue.isEmpty()){
            Coordinate curr = queue.poll();

            //early exit
            /*
            we don’t care about what k is left, we only care what is the minimum distance to reach here
             */
            if(curr.row == m-1 && curr.col == n-1){
                return curr.dist;
            }

            //explore all four directions
            int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                if(x>=0 && x<m && y>=0 && y<n){
                    int remainingK = curr.k - grid[x][y];
                    if (remainingK >= 0 && !visited[x][y][remainingK]) {
                        visited[x][y][remainingK] = true;
                        queue.offer(new Coordinate(x, y, remainingK, curr.dist + 1));
                    }
                }
            }
        }

        return -1;
    }

    class Coordinate{
        int row;
        int col;
        int k;
        int dist;

        public Coordinate(int row, int col, int k, int dist) {
            this.row = row;
            this.col = col;
            this.k = k;
            this.dist = dist;
        }
    }
}

