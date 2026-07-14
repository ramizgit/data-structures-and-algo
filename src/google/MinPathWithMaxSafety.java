package google;

import java.util.*;

public class MinPathWithMaxSafety {

    /*
    You are trapped in a grid where some cells contain walls, some are occupied by zombies,
    and others are open paths.
    Your goal is to find the shortest path from the start point to the gate (exit),
    while staying as far away from the zombies as possible.

Grid Details:

The grid is represented as a 2D matrix.
Each cell in the matrix can be one of the following:
'S': Start position (You begin at this location).
'G': Gate (Your goal is to reach this location).
'Z': Zombie (You should avoid these cells as much as possible).
'W': Wall (These cells are impassable).
'0': Open cell (You can move through these cells).
Movement:

You can move up, down, left, or right, but cannot move diagonally.
You cannot move through walls.
Your priority is to stay as far away from zombies as possible while still finding the shortest path to the gate.
Task:
Write a function that takes this matrix as input and returns the path so you stay as far as possible from zombie.
     */

    /*
    Approach:

    1. Multi-source BFS from all zombie cells to compute the minimum distance
       (safety factor) of every reachable cell from the nearest zombie.
       - Zombie cells have safety = 0.
       - Walls are marked as -1 (impassable).

    2. Binary search on the answer (minimum allowed safety factor).
       - For a candidate safety K, check if a path exists from S to G
         using only cells with safety >= K.
       - Reachability is monotonic:
           If a path exists for K, it also exists for all smaller values.
         Hence binary search is applicable.

    3. After finding the maximum possible safety factor, run one final BFS
       restricted to cells with safety >= maxSafety to obtain the shortest
       path satisfying that maximum safety.

    Time Complexity:
    - Multi-source BFS      : O(m * n)
    - Binary Search         : O(log(maxSafety))
    - Reachability check    : O(m * n) per iteration
    - Final BFS             : O(m * n)

    Overall: O(m * n * log(maxSafety))
    Space:   O(m * n)
    */

    int[][] DIRECTIONS = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    public void minPathWithMaxSafety(char[][] grid)
    {
        //input validation
        if(grid == null || grid.length == 0) {
            return;
        }

        //step1 - multi source bfs from zombie cells to calculate all other cells safety factor
        int m = grid.length;
        int n = grid[0].length;

        //find source and target cells
        int sr = 0, sc = 0, gr = 0, gc = 0; //source and target cells

        for(int i=0; i<m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 'S'){
                    sr = i;
                    sc = j;
                }else if(grid[i][j] == 'G'){
                    gr = i;
                    gc = j;
                }
            }
        }

        int[][] zombie = getGridWithZombieFactor(grid, m, n); //grid to hold how much distance each cell are from zombie cells

        //step2 - binary search to find path with max safety
        int maxSafety = maxSafetyFactor(zombie, m, n, grid, sr, sc, gr, gc);

        //step3 - final bfs only considering cells with max safety factor from step 2
        int shortest = shortestPathWithSafetyK(zombie, maxSafety, sr, sc, gr, gc, m, n);

        System.out.println("shortest path len = " + shortest);
    }

    private int shortestPathWithSafetyK(int[][] zombie, int k, int sr, int sc, int gr, int gc, int m, int n)
    {
        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State(sr, sc, 0)); //starting position

        boolean[][] visited = new boolean[m][n];
        visited[sr][sc] = true; //starting position

        State[][] parent = new State[m][n]; //to track parent cells to reconstruct path

        while(!queue.isEmpty()){

            State curr = queue.poll();

            if(curr.row == gr && curr.col == gc){
                //return curr.dist; //shortest path dist
                break; //After BFS reconstruct the path.
            }

            for(int[] dir : DIRECTIONS){

                int x = curr.row + dir[0];
                int y = curr.col + dir[1];

                if(x >= 0 && x < m && y >= 0 && y < n //boundary check
                        && !visited[x][y] //visited check
                        && zombie[x][y] >= k) //constraint check
                {
                    visited[x][y] = true;
                    queue.add(new State(x, y, curr.dist + 1));
                    parent[x][y] = new State(curr.row, curr.col); //track parent
                }
            }
        }

        //path construction logic
        List<int[]> path = new ArrayList<>();

        int r = gr, c = gc;

        while (r != sr || c != sc) {
            path.add(new int[]{r, c});

            State p = parent[r][c];
            r = p.row;
            c = p.col;
        }

        path.add(new int[]{sr, sc});
        Collections.reverse(path);

        System.out.println("shortest path = " + path);

        return -1;
    }

    private int[][] getGridWithZombieFactor(char[][] grid, int m, int n)
    {
        int[][] zombie = new int[m][n];
        Queue<State> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 'Z'){
                    zombie[i][j] = 0; //zombie cells are least safe, hence 0
                    queue.add(new State(i, j, 0)); //also collect cells for bfs
                    visited[i][j] = true; //mark them visited for bfs
                }else if(grid[i][j] == 'W'){
                    zombie[i][j] = -1; //no entry, hence putting -1 to mark walls
                }else{
                    zombie[i][j] = Integer.MAX_VALUE; //put dummy value for now, later during bfs change it to reflect actual dist from zombies
                }
            }
        }

        //bfs logic
        while(!queue.isEmpty()){

            State curr = queue.poll();

            for(int[] dir : DIRECTIONS){
                int x = curr.row + dir[0];
                int y = curr.col + dir[1];

                if(x >= 0 && x < m && y >= 0 && y < n //boundary check
                        && !visited[x][y] //visited check
                        && grid[x][y] != 'W') //wall constraint check
                {
                    zombie[x][y] = curr.dist + 1;
                    queue.add(new State(x, y, curr.dist + 1)); //add to queue for bfs
                    visited[x][y] = true; //mark visited
                }
            }
        }

        return zombie;
    }

    private int maxSafetyFactor(int[][] zombie, int m, int n, char[][] grid, int sr, int sc, int gr, int gc)
    {
        int low = 0;
        int high = 0;

        for(int i=0; i<m; i++) {
            for (int j = 0; j < n; j++) {
                high = Math.max(high, zombie[i][j]);
            }
        }

        int maxSafety = 0;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(canExit(zombie, mid, sr, sc, gr, gc, m, n)){
                maxSafety = mid; //possible max safety factor
                low = mid + 1; //can exit, try harder
            }else{
                high = mid - 1; //can't exit, try lower range
            }

        }

        return maxSafety;
    }

    private boolean canExit(int[][] zombie, int safety, int sr, int sc, int gr, int gc, int m, int n)
    {
        //dfs logic
        boolean[][] visited = new boolean[m][n];
        return dfs(zombie, safety, sr, sc, gr, gc, m, n, visited);
    }

    private boolean dfs(int[][] zombie, int safety, int i, int j, int gr, int gc, int m, int n, boolean[][] visited)
    {
        if(i == gr && j == gc){
            return true;
        }

        visited[i][j] = true;

        for(int[] dir : DIRECTIONS){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >= 0 && y < n //boundary check
                    && !visited[x][y] //visited check
                    && zombie[x][y] >= safety) //constraint check
            {
                if(dfs(zombie, safety, x, y, gr, gc, m, n, visited)){
                    return true;
                }
            }
        }

        return false;
    }

    static class State {
        int row;
        int col;
        int dist;

        public State(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        public State(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}


