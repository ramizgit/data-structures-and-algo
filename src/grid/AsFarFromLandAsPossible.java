package grid;

import java.util.*;

public class AsFarFromLandAsPossible {

    //https://leetcode.com/problems/as-far-from-land-as-possible/

    public int maxDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //collect all 1s and do multi source bfs to populate dist for 0 cells
        Queue<Coordinates> queue = new ArrayDeque<>(); //bfs queue

        //collect all 1 cells
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    queue.add(new Coordinates(i, j, 0));
                }
            }
        }

        //edge case if all 0 cells or all 1 cells
        if (queue.isEmpty() || queue.size() == m * n) {
            return -1;
        }

        boolean[][] visited = new boolean[m][n]; //to keep track of visited cells to avoid infinite loop
        int maxDist = -1;
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} }; //all four directions

        //bfs logic
        while(!queue.isEmpty()){
            Coordinates curr = queue.poll();

            //explore all four directions
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                if(x>=0 && x<m && y>=0 && y<n && grid[x][y] == 0 && !visited[x][y]){
                    visited[x][y] = true;
                    queue.add(new Coordinates(x, y, curr.dist + 1));

                    maxDist = Math.max(maxDist, curr.dist + 1);
                }
            }

        }

        return maxDist;
    }
}

class Coordinates{
    int row;
    int col;
    int dist;

    public Coordinates(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}
