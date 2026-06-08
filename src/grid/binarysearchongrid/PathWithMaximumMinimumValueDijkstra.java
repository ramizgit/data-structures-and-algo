package consistenthashing.grid.binarysearchongrid;

import java.util.*;

public class PathWithMaximumMinimumValueDijkstra {

    //https://leetcode.com/problems/path-with-maximum-minimum-value/

    /*
    Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and ending at (m - 1, n - 1) moving in the 4 cardinal directions.
    The score of a path is the minimum value in that path.
    For example, the score of the path 8 → 4 → 5 → 9 is 4.
     */

    public int maximumMinimumPath(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        //dijkstra algo
        //heap
        PriorityQueue<State> maxheap = new PriorityQueue<>( (a, b) -> b.maxScore - a.maxScore ); //maxheap by score so far
        maxheap.offer(new State(0, 0, grid[0][0])); //starting position

        //cost array
        int[][] score = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(score[i], Integer.MIN_VALUE); //initialize score with min possible value, will be relaxed later
        }
        score[0][0] = grid[0][0]; //starting position

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        while(!maxheap.isEmpty()){

            State curr = maxheap.poll();

            //staleness check
            if(curr.maxScore < score[curr.row][curr.col]){
                continue; //stale entry
            }

            //exit condition
            if(curr.row == m-1 && curr.col == n-1){
                return curr.maxScore; //target reached
            }

            //explore neighborus and do relaxation if needed
            for(int[] dir : directions){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                int newScore = Math.min(curr.maxScore, grid[newRow][newCol]); //we need min as path score is min value seen in the path

                if(newScore > score[newRow][newCol]){ //as we need to maximise the score
                    //do relaxation
                    score[newRow][newCol] = newScore;
                    maxheap.offer(new State(newRow, newCol, newScore));
                }
            }
        }

        return -1; //should not reach here
    }

    class State{
        int row;
        int col;
        int maxScore;

        public State(int row, int col, int maxScore) {
            this.row = row;
            this.col = col;
            this.maxScore = maxScore;
        }
    }
}
;