package grid.binarysearchongrid;

import java.util.*;

public class SwimInRisingWater {

    //https://leetcode.com/problems/swim-in-rising-water/description/

    //BINARY SEARCH APPROACH, DIJKSTRA APPROACH IS FOLLOWED BY
    //Time complexity :O(n*n) + O(n * n * logn) -----> O(n^2logn)
    public int swimInWater(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<m; i++){ //O(m*n)
            for(int j=0; j<n; j++){
                min = Math.min(min, grid[i][j]);
                max = Math.max(max, grid[i][j]);
            }
        }

        //binary search logic
        int low = min;
        int high = max;
        int answer = 0;

        while(low <= high){ //O(log(max - min))
            int mid = low + (high - low) / 2;

            if(canSwim(grid, m, n, mid)){ //O(m*n)
                answer = mid; //possible answer
                high = mid -1; //try lower to minimize time to swim across
            }else{
                low = mid + 1; //try higher
            }

        }

        return answer;
    }

    public boolean canSwim(int[][] grid, int m, int n, int time)
    {
        if (grid[0][0] > time) {
            return false; //early exit if cant swim
        }

        boolean[][] visited = new boolean[m][n];
        return dfs(grid, m, n, time, 0, 0, visited);
    }

    public boolean dfs(int[][] grid, int m, int n, int time, int i, int j, boolean[][] visited)
    {
        if(i == m-1 && j == n-1){
            return true;
        }

        visited[i][j] = true;

        //explore all possible directions
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x>=0 && x<m && y>=0 && y<n && !visited[x][y] && grid[x][y] <= time){
                if(dfs(grid, m, n, time, x, y, visited)){
                    return true;
                }
            }
        }

        return false;
    }

    //DIJKSTRA APPROACH
    public int swimInWater2(int[][] grid) {

        int n = grid.length;

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> a.cost - b.cost
        );

        // starting point
        dist[0][0] = grid[0][0];
        pq.offer(new State(0, 0, dist[0][0]));

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            int r = curr.row, c = curr.col;

            // ⚠️ skip outdated entry
            if (curr.cost > dist[r][c]) {
                continue;
            }

            // early exit
            if (r == n - 1 && c == n - 1) {
                return curr.cost;
            }

            //explore neighbours
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                int newCost = Math.max(curr.cost, grid[nr][nc]);

                // relax
                if (newCost < dist[nr][nc]) {
                    dist[nr][nc] = newCost;
                    pq.offer(new State(nr, nc, newCost));
                }
            }
        }

        return -1;
    }

    class State {
        int row, col, cost;
        State(int r, int c, int cost) {
            this.row = r;
            this.col = c;
            this.cost = cost;
        }
    }
}

/*
just a note, why plan DP won't work here.
in this grid, we can go all four directions, hence we can revisit nodes, hence CYCLE exists. DP breaks for circular dependency.
if movements were restricted only to right + down, grid becomes DAG, DP works
 */

