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

public class ShortestDistanceFromAllBuildings {

    //todo : implement

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

    public int shortestDistance(int[][] grid)
    {



        return 0;
    }
}
