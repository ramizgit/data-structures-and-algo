package bfs;

import matrix.Coordinates;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMinEffort {
    //https://leetcode.com/problems/path-with-minimum-effort/description/
    public static void main(String[] args)
    {
        int[][] heights = {{1,2,2},
                           {3,8,2},
                           {5,3,5}};
        System.out.println(minimumEffortPath(heights));
    }

    private static int minimumEffortPath(int[][] heights)
    {
        int m = heights.length;
        int n = heights[0].length;
        Queue<Coordinates> queue = new PriorityQueue<>( (a,b) -> a.dist - b.dist);
        int[][] effort = new int[m][n];
        for (int[] row : effort){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        
        //bfs
        queue.add(new Coordinates(0, 0, 0));
        effort[0][0] = 0;
        
        while (!queue.isEmpty()){
            Coordinates height = queue.poll();

            if(height.row == m-1 && height.col == n-1){
                //reached destination
                return height.dist;
            }

            for(int[] dir : directions){
                int x = height.row + dir[0];
                int y = height.col + dir[1];

                if(x >= 0 && x < m && y >= 0 && y < n){
                    int currEffort = Math.abs(heights[x][y] - heights[height.row][height.col]);
                    int maxEffort = Math.max(height.dist, currEffort);
                    if (maxEffort < effort[x][y]) {
                        effort[x][y] = maxEffort;
                        queue.add(new Coordinates(x, y, maxEffort));
                    }

                }
            }
        }
        return -1;
    }
}
