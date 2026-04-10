package grid;

import java.util.*;

public class ZeroOneMatrix {

    //https://leetcode.com/problems/01-matrix/description/

    public int[][] updateMatrix(int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        //approach : collect all 0 cells and do multi source bfs to update dist of 1 cells

        Queue<Coordinates> queue = new ArrayDeque<>(); //bfs queue
        boolean[][] visited = new boolean[m][n]; //to track visited cells to avoid infinite loop

        //collect all 0 cells
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == 0){
                    queue.add(new Coordinates(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} }; //all four directions

        //bfs logic
        while(!queue.isEmpty()){
            Coordinates curr = queue.poll();

            //explore all four directions
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                if(x>=0 && x<m && y>=0 && y<n && !visited[x][y]){
                    visited[x][y] = true;
                    queue.add(new Coordinates(x, y, curr.dist + 1));
                    mat[x][y] = curr.dist + 1; //update input matrix
                }
            }
        }

        return mat;
    }
}
