package grid.bfs;

/*
Shortest Distance from All Buildings

You are given an m x n grid where:

0 represents an empty land where you can build a house.
1 represents a building.
2 represents an obstacle that cannot be passed through.

You want to build exactly one house on an empty land (0).

Return the minimum total shortest-path distance from the house to all buildings.

Movement is allowed only in the four cardinal directions (up, down, left, right).

If it is impossible to reach every building from any empty land, return -1.

Method Signature
public int shortestDistance(int[][] grid)
Example 1
Input:

[
  [1,0,2,0,1],
  [0,0,0,0,0],
  [0,0,1,0,0]
]

Output:

7

Explanation

Build the house at (1,2) (0-indexed).

The shortest distances to the three buildings are:

3 + 3 + 1 = 7

which is the minimum possible.

Constraints
1 <= m, n <= 50

grid[i][j] ∈ {0, 1, 2}

There is at least one building.

Notes
Distance is the shortest path length, not the direct Manhattan distance, because obstacles may block the path.
The chosen empty land must be able to reach every building.
Return -1 if no such empty land exists.
 */

import java.util.*;

public class ShortestDistanceFromAllBuildings {

    /*
    /*
     * Hints:
     *
     * 1. Brute Force
     *    - Try every empty land (0) as the house location.
     *    - Run BFS to compute the shortest distance to all buildings.
     *    - Time Complexity: O(#emptyCells * m * n)
     *    - Can this pass for a 50 x 50 grid?
     *
     * 2. Think in Reverse
     *    - Instead of BFS from every empty land,
     *      can you BFS from every building?
     *
     * 3. During BFS from one building
     *    - For every reachable empty cell:
     *          distanceSum[row][col] += currentDistance;
     *          reachCount[row][col]++;
     *
     * 4. Maintain two matrices:
     *      distanceSum[][] -> total distance from all processed buildings
     *      reachCount[][]  -> number of buildings that reached this cell
     *
     * 5. After processing all buildings
     *    - A valid house location is an empty cell where:
     *          reachCount[row][col] == totalBuildings
     *    - Return the minimum distanceSum among all valid cells.
     *
     * Notes:
     *    - Use a fresh visited[][] for each building's BFS.
     *    - Do NOT use multi-source BFS.
     *      Multi-source BFS computes the distance to the nearest building,
     *      whereas this problem requires the sum of distances to all buildings.
     */

    final int[][] DIRECTIONS = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    public int shortestDistance(int[][] grid)
    {
        //input vadliation
        if(grid == null || grid.length == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        //populate dist grid
        int[][] distanceSum = new int[m][n];
        int[][] reachCount = new int[m][n];
        int totalBuildings = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    totalBuildings++; //buildings
                }
            }
        }

        //bfs from each building and accumulate dist for empty cells
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    bfs(i, j, grid, distanceSum, reachCount, m, n);
                }
            }
        }

        //find min dist
        int minDist = Integer.MAX_VALUE;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if (grid[i][j] == 0 && reachCount[i][j] == totalBuildings) {
                    minDist = Math.min(minDist, distanceSum[i][j]);
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private void bfs(int startRow, int startCol, int[][] grid, int[][] dist, int[][] reachCount, int m, int n)
    {
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(startRow, startCol, 0)); //starting building

        boolean[][] visited = new boolean[m][n];
        visited[startRow][startCol] = true;

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //explore neighborus
            for(int[] dir : DIRECTIONS){

                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                if(visited[newRow][newCol]){
                    continue; //already visited
                }

                if(grid[newRow][newCol] == 2  //obstacle
                        || grid[newRow][newCol] == 1 //another building
                )
                {
                    continue;
                }

                //process empty cell
                dist[newRow][newCol] += curr.dist + 1; //increment dist
                reachCount[newRow][newCol]++; //increment reach count
                visited[newRow][newCol] = true;
                bfsQueue.offer(new State(newRow, newCol, curr.dist + 1));
            }
        }
    }

    static class State{
        int row;
        int col;
        int dist;

        public State(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}


























