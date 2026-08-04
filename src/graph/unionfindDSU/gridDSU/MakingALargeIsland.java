package graph.unionfindDSU.gridDSU;

import graph.unionfindDSU.UnionFind;

import java.util.*;

public class MakingALargeIsland {

    //https://leetcode.com/problems/making-a-large-island/description/

    private static final int[][] UNION_DIRECTIONS = {
            {0, 1}, // Right
            {1, 0}  // Down
    };

    private static final int[][] NEIGHBOR_DIRECTIONS = {
            {0, 1},  // Right
            {0, -1}, // Left
            {1, 0},  // Down
            {-1, 0}  // Up
    };

    public int largestIsland(int[][] grid) {

        /*
        Grid

        ↓

        Create DSU

        ↓

        Merge all adjacent 1s

        ↓

        Each island now knows its size

        ↓

        For every 0

            Look at 4 neighbors

            Get each neighbor's island root

            Ignore duplicate roots

            Sum all unique island sizes

            +1 for flipped cell

        ↓

        Take maximum area

        ↓

        Return answer
         */

        //input validation
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int n = grid.length;

        UnionFind uf = new UnionFind(n * n);

        //merge each land cell with its adjacent land neighbors to build connected island components
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) {
                    continue; //not a land cell
                }

                int currCellId = i * n + j; //convert to an int id for dsu to work

                //union adjacent 1s
                for (int[] dir : UNION_DIRECTIONS) {
                    int newRow = i + dir[0];
                    int newCol = j + dir[1];

                    //boundary check
                    if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n) {
                        continue; //out of boundary
                    }

                    if (grid[newRow][newCol] == 0) {
                        continue; //not a land cell
                    }

                    int newCellId = newRow * n + newCol;

                    uf.union(currCellId, newCellId);
                }
            }
        }

        //treat each water cell as land and calculate the resulting merged island size.
        int largest = 0;
        boolean hasZero = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    continue; //not a water cell
                }

                hasZero = true; //handle edge case where the grid contains no water cells

                int area = 1;
                Set<Integer> visited = new HashSet<>(); //track visited island components to prevent duplicate counting.

                //union adjacent 1s
                for (int[] dir : NEIGHBOR_DIRECTIONS) {

                    int newRow = i + dir[0];
                    int newCol = j + dir[1];

                    //boundary check
                    if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n) {
                        continue; //out of boundary
                    }

                    if (grid[newRow][newCol] == 0) {
                        continue;
                    }

                    int newCellId = newRow * n + newCol;

                    int parent = uf.find(newCellId);

                    if (!visited.contains(parent)) {
                        visited.add(parent);
                        area += uf.getNodeSize()[parent];
                    }
                }

                largest = Math.max(largest, area);
            }
        }

        if (!hasZero) {
            return n * n; //if no 0 exists, the largest island is already the entire grid, so we return n * n
        }

        return largest;
    }
}
