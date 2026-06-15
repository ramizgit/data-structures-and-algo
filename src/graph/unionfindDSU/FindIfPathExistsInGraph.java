package consistenthashing.graph.unionfindDSU;

public class FindIfPathExistsInGraph {

    //https://leetcode.com/problems/find-if-path-exists-in-graph/
    public boolean validPath(int n, int[][] edges, int source, int destination)
    {
        graph.unionfindDSU.UnionFind uf = new graph.unionfindDSU.UnionFind(n);

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            uf.union(u, v);
        }

        return uf.find(source) == uf.find(destination);
    }

}


