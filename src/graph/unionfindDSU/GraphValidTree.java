package graph.unionfindDSU;

public class GraphValidTree {

    //https://leetcode.com/problems/graph-valid-tree/description/

    /*
    criteria:
    1. no loop
    2. all vertices connected as one component
    3. a tree with n nodes must have exactly n - 1 edges
     */

    public boolean validTree(int n, int[][] edges)
    {
        if(edges.length != n-1){
            return false; //a tree with n nodes must have exactly n - 1 edges.
        }

        //use union find utility to detect cycle
        UnionFind unionFind = new UnionFind(n);

        for(int[] edge : edges){
            int src = edge[0];
            int des = edge[1];

            if(!unionFind.union(src, des)){
                return false; //detects cycle
            }
        }

        return true; //if a graph has exactly n - 1 edges AND no cycles → it must be connected
    }
}
