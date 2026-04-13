package graph.unionfindDSU;

public class NumberOfConnectedComponents {

    /*
    APPROACH:
    start with n components and reduce the count each time we successfully union two nodes.
    the final count gives the number of connected components
     */

    private static int countConnectedComponents(int n, int[][] edges)
    {
        UnionFind unionFind = new UnionFind(n);
        int components = n; //start with n isolated components for each n nodes

        for(int[] edge : edges){
            int src = edge[0];
            int des = edge[1];

            if(unionFind.union(src, des)){
                components--; // if union succeeds, merge two components, hence count reduced
            }
        }

        return components;
    }
}
