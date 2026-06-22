package graph.mst;

import graph.unionfindDSU.UnionFind;

import java.util.*;

//kruskal algo is edge driven while prim's is node driven
public class KruskalMST {

    public int mstCost(int n, int[][] edges) {

        // Kruskal processes edges in increasing order of weight, hence sorting
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        UnionFind dsu = new UnionFind(n);

        int totalCost = 0;
        int edgeCount = 0;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (dsu.union(u, v)) {
                totalCost += w; //add to MST
                edgeCount++; //increase edge count, as kruskal is edge-driven algorithm

                if (edgeCount == n - 1) {
                    return totalCost; // A spanning tree with n nodes always contains exactly (n - 1) edges.
                }
            }
        }


        return -1; // Graph is disconnected, so MST cannot be formed.
    }
}
