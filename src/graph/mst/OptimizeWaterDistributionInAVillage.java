package graph.mst;

import graph.unionfindDSU.UnionFind;

import java.util.*;

public class OptimizeWaterDistributionInAVillage {

    //https://leetcode.com/problems/optimize-water-distribution-in-a-village/description/

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes)
    {
        int[][] pipesWithWells = new int[pipes.length + n][3];

        // copy pipes
        for (int i = 0; i < pipes.length; i++) {
            pipesWithWells[i] = pipes[i];
        }

        // add wells edges
        for (int i = 0; i < n; i++) {
            pipesWithWells[pipes.length + i] = new int[]{0, i + 1, wells[i]};
        }

        Arrays.sort(pipesWithWells, (a, b) -> Integer.compare(a[2], b[2]));

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

                if(edgeCount == n){
                    break;
                }
            }
        }

        return edgeCount == n ? totalCost : -1;
    }
}
