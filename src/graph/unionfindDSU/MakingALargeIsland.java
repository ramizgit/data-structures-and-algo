package graph.unionfindDSU;

import java.util.*;

public class MakingALargeIsland {

    public int largestIsland(int[][] grid) {
        //input validation
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int n = grid.length;

        UnionFind uf = new UnionFind(n * n);

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) {
                    continue;
                }

                int currCellId = i * n + j;

                //union adjacent 1s
                for (int[] dir : directions) {
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

                    uf.union(currCellId, newCellId);
                }
            }
        }

        //find largest
        int largest = 0;
        boolean hasZero = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    continue;
                }

                hasZero = true;

                int area = 1;
                Set<Integer> visited = new HashSet<>();

                //union adjacent 1s
                for (int[] dir : directions) {

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
                        area += uf.size[parent];
                    }
                }

                largest = Math.max(largest, area);
            }
        }

        if (!hasZero) {
            return n * n;
        }

        return largest;
    }
}
