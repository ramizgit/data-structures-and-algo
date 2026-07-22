package graph.dijkstra;

import java.util.*;

public class MinimumTimeToVisitACellInAGrid {

    //https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/description/

    //important : waiting is NOT allowed, we must make a move every second

    public int minimumTime(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        // Edge case : At time 1 you must move. If both neighbors require time > 1, you are stuck forever.
        if (m == 1 && n == 1) {
            return 0;
        }
        if (m == 1) {
            if (grid[0][1] > 1) return -1;
        }
        else if (n == 1) {
            if (grid[1][0] > 1) return -1;
        }
        else if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }

        //minheap
        PriorityQueue<State> minheap = new PriorityQueue<>( (a, b) -> a.time - b.time );
        minheap.offer(new State(0, 0, 0)); //starting cell at time 0

        //cost array dist[i][j] = min time to reach cell (i, j)
        int[][] dist = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0; //starting cell at time 0

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        while(!minheap.isEmpty()){

            State curr = minheap.poll();

            //staleness check
            if(curr.time > dist[curr.row][curr.col]){
                continue;
            }

            //exit condition
            if(curr.row == m - 1 && curr.col == n - 1){
                return curr.time;
            }

            //explore nieghbours and do relaxation
            for(int[] dir : directions){

                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                int t = curr.time;
                int unlock = grid[newRow][newCol];
                int arrivalTime;

                if(t + 1 >= unlock){
                    arrivalTime = t + 1; //neighbour cell is reachable immediately
                }else{
                    //neighbour cell is NOT reachable immediately
                    //need to waste time by moving between cells.
                    //adjust arrival time based on parity.

                    int wait = unlock - (t + 1);

                    if(wait % 2 == 0){
                        arrivalTime = unlock;
                    }else{
                        arrivalTime = unlock + 1;
                    }
                }

                /*
                IMPORTANT: IF WAITING IS ALLOWED, THEN SIMPLY DO FOLLOWING
                arrivalTime = Math.max(t + 1, unlock);
                 */

                if(arrivalTime < dist[newRow][newCol]){
                    //relaxation
                    dist[newRow][newCol] = arrivalTime;
                    minheap.offer(new State(newRow, newCol, arrivalTime));
                }
            }
        }

        return -1;
    }

    class State{
        int row;
        int col;
        int time;

        public State(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
