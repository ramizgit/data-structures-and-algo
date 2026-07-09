package graph.unionfindDSU;

public class NumberOfConnectedComponents {

    /*
    APPROACH:
    start with n components and reduce the count each time we successfully union two nodes.
    the final count gives the number of connected components
     */

    public int countConnectedComponents(int n, int[][] edges)
    {
        //initialize union find - O(n)
        graph.unionfindDSU.UnionFind unionFind = new graph.unionfindDSU.UnionFind(n);

        int components = n; //start with n isolated components for each n nodes

        for(int[] edge : edges){

            int u = edge[0];
            int v = edge[1];

            if(unionFind.union(u, v)){
                components--; // if union succeeds, merge two components, hence count reduced
            }
        }

        return components;
    }
}
