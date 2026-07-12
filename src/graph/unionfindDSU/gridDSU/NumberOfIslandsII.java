package graph.unionfindDSU.gridDSU;

import graph.unionfindDSU.UnionFind;

import java.util.*;

public class NumberOfIslandsII {

    //https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0305.Number%20of%20Islands%20II/README_EN.md

    /*
    Approach:
    1. Treat every new land cell as a new island.
    2. Increment island count.
    3. Union with all neighbouring land cells.
    4. Every successful union merges two islands, so decrement the count.
    5. Record the island count after each operation.

    Time : O(k * α(m * n))
    Space: O(m * n)

    where k = positions.length
    */

    public List<Integer> numIslands2(int m, int n, int[][] positions)
    {
        boolean[][] land = new boolean[m][n]; //initially starts with all water cells

        UnionFind unionFind = new UnionFind(m * n);
        List<Integer> result = new ArrayList<>();
        int islands = 0;

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} }; //neighbours

        for(int[] position : positions){

            int row = position[0];
            int col = position[1];

            if(land[row][col]){ //dupe, already land, nothing should change
                result.add(islands);
                continue;
            }

            land[row][col] = true;  //mark current cell as land
            islands++; //increment island count, new land initially forms a new island

            int currentId = row * n + col; //union-find operates on 1D ids, so map each (row, col) to a unique id.

            //explore neighbours
            for(int[] dir : directions){

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                //land check
                if(!land[newRow][newCol]){
                    continue; //no land
                }

                int neighbourId = newRow * n + newCol;

                if(unionFind.union(currentId, neighbourId)){
                    islands--; // if union succeeds → merge islands, as successful union merges two previously separate islands
                }
            }

            result.add(islands);
        }

        return result;
    }
}
