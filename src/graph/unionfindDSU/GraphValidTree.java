package graph.unionfindDSU;

public class GraphValidTree {

    //https://leetcode.com/problems/graph-valid-tree/description/

    /*
    criteria:
    1. a tree with n nodes must have exactly n - 1 edges
    2. no cycle
    3. all vertices connected as one component
     */

    public boolean validTree(int n, int[][] edges)
    {
        //input validation
        if (n <= 0 || edges == null) {
            return false;
        }

        if(edges.length != n-1){
            return false; //a tree with n nodes must have exactly n - 1 edges.
        }

        //use union find utility to detect cycle
        graph.unionfindDSU.UnionFind unionFind = new graph.unionfindDSU.UnionFind(n);

        for(int[] edge : edges){

            int u = edge[0];
            int v = edge[1];

            if(!unionFind.union(u, v)){
                return false; //detects cycle
            }
        }

        return true; //if a graph has exactly n - 1 edges AND no cycles → it must be connected as one component
    }
}
