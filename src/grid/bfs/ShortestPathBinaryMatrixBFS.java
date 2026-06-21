package consistenthashing.grid.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathBinaryMatrixBFS {

    //https://leetcode.com/problems/shortest-path-in-binary-matrix/description/

    public int shortestPathBinaryMatrix(int[][] grid)
    {
        if(grid == null || grid[0][0] != 0){
            return -1;
        }

        int n = grid.length; //n*n grid

        //bfs queue
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new State(0, 0, 1)); //starting position

        boolean[][] visited = new boolean[n][n]; //for visited tracking
        visited[0][0] = true; //starting position

        //all possible directions
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1} };

        while (!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //exit condition
            if(curr.row == n-1 && curr.col == n-1){
                return curr.length;
            }

            //explore neighbours
            for(int[] dir : directions){
                int x = curr.row + dir[0];
                int y = curr.col + dir[1];

                if(x >= 0 && y >= 0 && x < n && y < n && grid[x][y] != 1 && !visited[x][y]){
                    bfsQueue.add(new State(x, y, curr.length+1));
                    visited[x][y] = true;
                }
            }
        }

        return -1;
    }

    class State{
        int row;
        int col;
        int length;

        public State(int row, int col, int length) {
            this.row = row;
            this.col = col;
            this.length = length;
        }
    }
}

