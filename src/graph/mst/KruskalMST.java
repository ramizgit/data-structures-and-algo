package graph.mst;

import graph.unionfindDSU.UnionFind;

import java.util.*;

public class KruskalMST {

    public int mstCost(int n, int[][] edges) {

        // sort edges by weight
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

                // MST has (n - 1) edges
                if (edgeCount == n - 1) {
                    break;
                }
            }
        }

        // if not all nodes connected
        return edgeCount == n - 1 ? totalCost : -1;
    }
}
