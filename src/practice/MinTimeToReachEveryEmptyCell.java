package consistenthashing.practice;

import java.util.*;

public class MinTimeToReachEveryEmptyCell {

    /*
    → BFS
    → Multi-source BFS
    → Dijkstra
    → Min-Max Dijkstra
    → State-space Dijkstra
     */

    //plain multi source BFS
    //Time complexity : O(m*n)
    public int minimumTime(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        //iterate the grid to collect all startig points for bfs, also count empty cells
        Queue<State> bfsQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n]; //to avoid infinite loop during fbs
        int emptyCellCount = 0;

        //time : O(m*n)
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 2){
                    bfsQueue.offer(new State(i, j, 0)); //starting cell with time 0
                    visited[i][j] = true;
                }else if(grid[i][j] == 0){
                    emptyCellCount++;
                }
            }
        }

        //edge case - no empty cell
        if(emptyCellCount == 0){
            return 0;
        }

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} }; //all four directions

        //time : O(m*n)
        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //explore neighbours
            for(int[] dir : directions){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //check boundary
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                //visited check
                if(visited[newRow][newCol]){
                    continue;
                }

                //obstacle check
                if(grid[newRow][newCol] == 1){
                    continue; //hit obstacle
                }

                //process cell and reduce empty cell count
                bfsQueue.offer(new State(newRow, newCol, curr.time + 1));
                visited[newRow][newCol] = true;
                if(grid[newRow][newCol] == 0){
                    emptyCellCount--;
                }

                //exit condition
                if(emptyCellCount == 0){
                    return curr.time + 1;
                }
            }
        }

        return -1;
    }

    //dijkstra version
    //cost of path = sum of cell values
    /*
    V = m*n
    E ≈ 4*m*n

    O(E log V) = O(m*n log(m*n))
     */
    //Time complexity : O(m*n*log(m*n))
    public int minimumTime2(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        //edge case starting cell is blocked
        if(grid[0][0] == -1){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        PriorityQueue<State> minheap = new PriorityQueue<>( (a, b) -> a.time - b.time ); //always process the node with the smallest time first
        minheap.offer(new State(0, 0, 0)); //starting cell

        //cost array where time[i][j] = min time to reach cell (i, j)
        int[][] time = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(time[i], Integer.MAX_VALUE); //initialize with max possible value, to be relaxed later
        }
        time[0][0] = 0; //starting cell

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} }; //all four directions

        //O(m*n)
        while(!minheap.isEmpty()){

            State curr = minheap.poll(); //time : log(m*n)

            //staleness check
            if(curr.time > time[curr.row][curr.col]){
                continue;
            }

            //exit condition
            if(curr.row == m-1 && curr.col == n-1){
                return curr.time; //target reached
            }

            //explore neighbours and do relaxation
            for(int[] dir : directions){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //check boundary
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                //obstacle check
                if(grid[newRow][newCol] == -1){
                    continue; //hit obstacle
                }

                int newTime = curr.time + grid[newRow][newCol];


                if(newTime < time[newRow][newCol]){
                    //relaxation
                    time[newRow][newCol] = newTime;
                    minheap.offer(new State(newRow, newCol, newTime));
                }
            }
        }

        return -1;
    }

    //modified dijkstra version
    //cost of path = maximum cell value encountered on the path
    /*
    V = m*n
    E ≈ 4*m*n

    O(E log V) = O(m*n log(m*n))
     */
    //Time complexity : O(m*n*log(m*n))
    public int minimumTime3(int[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        //edge case starting cell is blocked
        if(grid[0][0] == -1){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        PriorityQueue<State> minheap = new PriorityQueue<>( (a, b) -> a.time - b.time ); //always process the node with the smallest time first
        minheap.offer(new State(0, 0, grid[0][0])); //starting cell with code grid[0][0]

        //cost array where time[i][j] = min time to reach cell (i, j)
        int[][] time = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(time[i], Integer.MAX_VALUE); //initialize with max possible value, to be relaxed later
        }
        time[0][0] = grid[0][0]; //starting cell

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} }; //all four directions

        //O(m*n)
        while(!minheap.isEmpty()){

            State curr = minheap.poll(); //time : log(m*n)

            //staleness check
            if(curr.time > time[curr.row][curr.col]){
                continue;
            }

            //exit condition
            if(curr.row == m-1 && curr.col == n-1){
                return curr.time; //target reached
            }

            //explore neighbours and do relaxation
            for(int[] dir : directions){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //check boundary
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                //obstacle check
                if(grid[newRow][newCol] == -1){
                    continue; //hit obstacle
                }

                int newTime = Math.max(curr.time, grid[newRow][newCol]);

                if(newTime < time[newRow][newCol]){
                    //relaxation
                    time[newRow][newCol] = newTime;
                    minheap.offer(new State(newRow, newCol, newTime));
                }
            }
        }

        return -1;
    }

    //binary search approach
    /*
    O(m*n*log(max value range))
    binary search is applicable due to monotonic feature on the answer range
    it does multiple bfs pass though
     */
    //todo : code


    //modified dijkstra
    //You are allowed to eliminate at most k obstacles.
    public int minimumCost5(int[][] grid, int k)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        PriorityQueue<StateWithK> minheap = new PriorityQueue<>( (a, b) -> a.time - b.time ); //always process the node with the smallest time first
        minheap.offer(new StateWithK(0, 0, k, 0)); //starting cell

        //cost array time[i][j][k] = min time to reach cell i,j with k obstalces remaining
        int[][][] time = new int[m][n][k+1];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                Arrays.fill(time[i][j], Integer.MAX_VALUE); //initialize with max possible value, to be relaxed later
            }
        }
        time[0][0][k] = 0; //starting cell

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} }; //all four directions

        while(!minheap.isEmpty()){

            StateWithK curr = minheap.poll();

            //staleness check
            if(curr.time > time[curr.row][curr.col][curr.remainingK]){
                continue;
            }

            //exit condition
            if(curr.row == m-1 && curr.col == n-1){
                return curr.time;
            }

            //explore neighbours and do relaxation
            for(int[] dir : directions){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //check boundary
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                //obstacle check
                if(grid[newRow][newCol] == -1 && curr.remainingK == 0){
                    continue;
                }

                int newTime;
                int newK;

                if(grid[newRow][newCol] == -1){
                    newTime = curr.time; //0 additional cost to enter the new cell
                    newK = curr.remainingK - 1;
                }else{
                    newTime = curr.time + grid[newRow][newCol];
                    newK = curr.remainingK;
                }

                if(newTime < time[newRow][newCol][newK]){
                    //relaxation
                    time[newRow][newCol][newK] = newTime;
                    minheap.offer(new StateWithK(newRow, newCol, newK, newTime));
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

    class StateWithK{
        int row;
        int col;
        int remainingK;
        int time;

        public StateWithK(int row, int col, int remainingK, int time) {
            this.row = row;
            this.col = col;
            this.remainingK = remainingK;
            this.time = time;
        }
    }
}

/*
| Problem                                                                               | Core Idea                                                    |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| **Visit all reachable cells from one start in an unweighted grid**                    | **BFS**                                                      |
| **Visit all reachable cells from multiple starts in an unweighted grid**              | **Multi-source BFS**                                         |
| **Shortest path where moving into a cell has arbitrary non-negative cost**            | **Dijkstra**                                                 |
| **Path cost = maximum cell value encountered on the path**                            | **Modified Dijkstra** (`newCost = max(currCost, cellValue)`) |
| **Path cost = maximum cell value encountered on the path** (alternative)              | **Binary Search + BFS/DFS** using monotonic feasibility      |
| **Shortest path with arbitrary non-negative costs + can eliminate up to K obstacles** | **State-Space Dijkstra** with state `(row, col, remainingK)` |
| **Minimum number of steps + can eliminate up to K obstacles**                         | **State-Space BFS** with state `(row, col, remainingK)`      |
 */