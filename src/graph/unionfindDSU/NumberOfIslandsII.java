package graph.unionfindDSU;

import java.util.*;

public class NumberOfIslandsII {

    public List<Integer> numIslands2(int m, int n, int[][] positions)
    {
        //conver 2D to 1D for union find to work
        boolean[] islands = new boolean[m * n];
        UnionFind unionFind = new UnionFind(m * n);
        List<Integer> result = new ArrayList<>();
        int count = 0;
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} }; //neighbours

        for(int[] pos : positions){
            int r = pos[0];
            int c = pos[1];

            int id = r * n + c;

            if(islands[id]){ //dupe, already island
                result.add(count);
                continue;
            }

            islands[id] = true; //mark island
            count++; //increment island count

            //explore neighbours
            for(int[] dir : directions){
                int x = r + dir[0];
                int y = c + dir[1];

                if(x>=0 && x<m && y>=0 && y<n){ // boundary check
                    int neighbourId = x * n + y;
                    if(islands[neighbourId]){
                        if(unionFind.union(id, neighbourId)){
                            count--; // if union succeeds → merge islands
                        }
                    }
                }
            }

            result.add(count);
        }

        return result;
    }
}
