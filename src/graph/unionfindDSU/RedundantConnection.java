package graph.unionfindDSU;

import java.util.*;

public class RedundantConnection {

    /*
    APPROACH :
    process edges one by one and use Union-Find to check if two nodes are already connected.
    if they are already connected, adding the edge forms a cycle, so return it
     */

    public int[] findRedundantConnection(int[][] edges) {

        //check cycle before adding edge
        UnionFind unionFind = new UnionFind(edges.length + 1);

        for(int[] edge : edges){
            int src = edge[0];
            int des = edge[1];

            if(!unionFind.union(src, des)){
                return edge;
            }
        }

        return new int[0];
    }
}
