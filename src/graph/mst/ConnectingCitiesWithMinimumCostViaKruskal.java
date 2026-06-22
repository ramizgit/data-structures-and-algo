package consistenthashing.graph.mst;

import java.util.*;

public class ConnectingCitiesWithMinimumCostViaKruskal {

    //https://leetcode.com/problems/connecting-cities-with-minimum-cost/

    // Time  : O(V + E log E)
    // Space : O(n)
    public int getMinimumCost(int n, int[][] connections)
    {
        //input is edge list, hence kruskal, using Prim would first require building an adjacency list.

        //input validation
        if (connections == null || connections.length == 0) {
            return -1;
        }

        //sort edges by increasing cost - O(E log E)
        Arrays.sort(connections, (a, b) -> Integer.compare(a[2], b[2]));

        //initialize DSU - O(V)
        graph.unionfindDSU.UnionFind uf = new graph.unionfindDSU.UnionFind(n+1); // cities are 1-indexed

        int totalCost = 0;
        int edgeCount = 0;

        for(int[] connection : connections){ //O(E)

            int u = connection[0];
            int v = connection[1];
            int cost = connection[2];

            if(uf.union(u, v)){ // Amortized α(V) ≈ O(1)
                totalCost += cost;
                edgeCount++;

                if(edgeCount == n-1){
                    return totalCost;
                }
            }
        }

        return -1;
    }
}
