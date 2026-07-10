package graph.misc;

import java.util.*;

public class FlowerPlantingWithNoAdjacent {

    /*
    Approach:
    1. Build an adjacency list.
    2. Iterate through every garden.
    3. Mark the flower types already used by its neighbours.
    4. Assign the first available flower type (1–4).

    Since every garden has at most 3 neighbours, at least one of the
    4 flower types is always available.

    Time : O(V + E)
    Space: O(V + E)
    */

    public int[] gardenNoAdj(int n, int[][] paths)
    {
        //build graph as adj. list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] path : paths){
            int u = path[0];
            int v = path[1];

            graph.get(u).add(v);
            graph.get(v).add(u); // since the graph is undirected
        }

        int[] color = new int[n+1];

        for(int i=1; i<=n; i++){ //iterate each node

            boolean[] used = new boolean[5];

            //mark colors already assigned to neighbouring gardens.
            for(int neighbour : graph.get(i)){
                used[color[neighbour]] = true;
            }

            //assign first unused color
            for(int j=1; j<=4; j++){
                if(!used[j]){
                    color[i] = j;
                    break;
                }
            }

        }

        return Arrays.copyOfRange(color, 1, n + 1);
    }
}
