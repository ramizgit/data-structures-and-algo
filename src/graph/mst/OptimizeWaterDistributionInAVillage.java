package graph.mst;

import graph.unionfindDSU.UnionFind;

import java.util.*;

public class OptimizeWaterDistributionInAVillage {

    //https://leetcode.com/problems/optimize-water-distribution-in-a-village/description/

    /*
    APPROACH (Kruskal + Virtual Node)

    Think of the ground as an infinite water source (virtual node 0).

    For every house, digging a well is equivalent to connecting that house
    to the ground with an edge whose cost is the well construction cost.

    So:
    - Pipe       -> edge between two houses.
    - Well       -> edge from virtual node 0 to the house.

    After adding these virtual edges, the problem becomes a standard
    Minimum Spanning Tree. Run Kruskal's algorithm, which automatically
    chooses the cheaper option:
    - Select a virtual edge -> build a well.
    - Select a pipe edge    -> connect through another house.

    Since there are (n + 1) nodes (including the virtual node 0),
    the MST contains exactly n edges.
    */

    // Time  : O(E log E). E is pipes length (edges)
    // Space : O(E + V)
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes)
    {
        // Create a new edge list to store both the original pipes and the virtual well edges.
        int[][] pipesWithWells = new int[pipes.length + n][3];

        // add pipe edges
        for (int i = 0; i < pipes.length; i++) {
            pipesWithWells[i] = pipes[i];
        }

        // add well edges
        for (int i = 0; i < n; i++) {
            pipesWithWells[pipes.length + i] = new int[]{0, i + 1, wells[i]}; //+1 due to indexing mismatch between the wells array and the house numbering
        }

        //sort by increasing cost for kruskal algo to work
        Arrays.sort(pipesWithWells, (a, b) -> Integer.compare(a[2], b[2]));

        //initialize DSU
        UnionFind dsu = new UnionFind(n+1);

        int totalCost = 0;
        int edgeCount = 0;

        for(int[] edge : pipesWithWells){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if(dsu.union(u, v)){
                totalCost += w;
                edgeCount++;

                if(edgeCount == n){ //Since the graph has (n + 1) nodes (including node 0), the MST contains exactly n edges.
                    return totalCost;
                }
            }
        }

        return -1;
    }
}
