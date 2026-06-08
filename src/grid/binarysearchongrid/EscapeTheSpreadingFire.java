package consistenthashing.grid.binarysearchongrid;

import java.util.*;

public class EscapeTheSpreadingFire {

    //https://leetcode.com/problems/escape-the-spreading-fire/description/

    /*
    Approach :-

    Multi-source BFS
    +
    Binary Search on Answer
    +
    BFS feasibility check
     */

    private static final int[][] DIRECTIONS = {
            {0, 1}, //right
            {0, -1}, //left
            {1, 0}, //down
            {-1, 0} }; //up

    public int maximumMinutes(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        //collect all fire cells for multi source bfs
        Queue<State> fireBfsQueue = new ArrayDeque<>(); //queue for bfs
        boolean[][] visited = new boolean[m][n]; //visited tracking during bfs

        int[][] fireTime = new int[m][n]; //grid to hold fire timeline
        for(int i=0; i<m; i++){
            Arrays.fill(fireTime[i], Integer.MAX_VALUE);
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    fireBfsQueue.offer(new State(i, j, 0));
                    visited[i][j] = true;
                    fireTime[i][j] = 0;
                }
            }
        }

        while(!fireBfsQueue.isEmpty()){
            State curr = fireBfsQueue.poll();

            //explore neighbours
            for(int[] dir : DIRECTIONS){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                //boundary check
                if(x < 0 || x>= m || y < 0 || y >= n){
                    continue; //out of boundary
                }

                //wall check
                if(grid[x][y] == 2){
                    continue; //wall, cant continue
                }

                //visited check
                if(visited[x][y]){
                    continue; //already visited
                }

                int newFireTime = curr.time + 1;

                //enqueue
                fireBfsQueue.offer(new State(x, y, newFireTime));

                //mark visited
                visited[x][y] = true;

                //populate fire timeline grid
                fireTime[x][y] = newFireTime;
            }
        }

        //now do binary search for feasibility check to get max wait time
        int low = 0;
        /*
        After m*n minutes, the fire has already done everything it can do. There are no new cells left to burn. The fire state becomes stable.
        hence high = m * n
         */
        int high = m * n; //if we can survive for m*n minutes, then we can survive forever.
        int answer = -1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(canSafelyEscape(grid, fireTime, mid, m, n)){
                answer = mid; //possible answer
                low = mid + 1; //try higher to maximize wait time
            }else{
                high = mid - 1; //try lower as cant safely pass with current wait time
            }
        }

        return answer == m * n ? 1_000_000_000 : answer;
    }

    private boolean canSafelyEscape(int[][] grid, int[][] fireTime, int waitTime, int m, int n)
    {
        if(waitTime >= fireTime[0][0]){
            return false; //cannot even stay initially
        }

        Queue<State> playerBfsQueue = new ArrayDeque<>();
        playerBfsQueue.offer(new State(0, 0, waitTime)); //starting cell

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true; //starting cell

        while(!playerBfsQueue.isEmpty()){
            State curr = playerBfsQueue.poll();

            //early exit - safehouse reached
            if(curr.row == m-1 && curr.col == n-1){
                return true;
            }

            //explore neighbours
            for(int[] dir : DIRECTIONS){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                //boundary check
                if(x < 0 || x>= m || y < 0 || y >= n){
                    continue; //out of boundary
                }

                //wall check
                if(grid[x][y] == 2){
                    continue; //wall, cant continue
                }

                //visited check
                if(visited[x][y]){
                    continue; //already visited
                }

                int newTime = curr.time + 1;

                boolean isSafeHouse = (x == m - 1 && y == n - 1);

                //SPECIAL SAFEHOUSE RULE
                if(isSafeHouse){
                    //can arrive same time as fire
                    if(newTime > fireTime[x][y]){
                        continue;
                    }
                }else{
                    //must arrive strictly before fire
                    if(newTime >= fireTime[x][y]){
                        continue;
                    }
                }

                playerBfsQueue.offer(new State(x, y, newTime)); //enqueue
                visited[x][y] = true; //mark visited
            }
        }

        return false;
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
