package graph.dfs;

import java.util.*;

public class MinEdgesToReverse {

    //https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable/description/

    /*
    Return an integer array answer, where answer[i] is the minimum number of edge reversals required
    so it is possible to reach any other node starting from node i through a sequence of directed edges.
     */

    public int[] minEdgeReversals(int n, int[][] edges)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges as per inputs
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(new Edge(v, 0)); //Original edges with cost 0
            graph.get(v).add(new Edge(u, 1)); //Artificial reverse edges with cost 1
            /*
            important : Notice this is opposite of Problem ReorderRoutes
            Problem 1466 asks
            every node should reach 0
            Problem 2858 asks
            0 should reach every node
            The meaning of "good" and "bad" directions flips.
             */
        }

        //compute answer for root which is ans[0] using dfs
        int[] ans = new int[n];
        dfsTotalRootCount(0, -1, ans, graph);

        // run re-root dfs logic to compute answer for all other nodes
        dfsReroot(0, -1, ans, graph);

        return ans;
    }

    // counts reversals needed so that all nodes reachable from root (0)
    public void dfsTotalRootCount(int node, int parent, int[] cost, Map<Integer, List<Edge>> graph)
    {
        //explore neighbours
        for(Edge neighbour : graph.get(node)){
            if(neighbour.v != parent){ //ensures recursion only goes to children, not back to parent to avoid infinite loop
                cost[0] += neighbour.w;
                dfsTotalRootCount(neighbour.v, node, cost, graph);
            }
        }
    }

    private void dfsReroot(int node, int parent, int[] ans, Map<Integer, List<Edge>> graph)
    {
        //explore neighbours
        for(Edge neighbour : graph.get(node)){
            //int v = neighbour.v;
            if(neighbour.v != parent){ //to avoid infinite loop, ensures recursion only goes to children, not parent
                if(neighbour.w == 0){
                    // Original edge: node -> child. When child becomes the root, this edge must be reversed. hence +1
                    ans[neighbour.v] = ans[node] + 1; //parent node didnt pay to come to v as original edge exists, hence we need to add +1 to go from v to node via reverse edge
                }else{
                    // Reverse edge: child -> node. This reversal was needed for the parent root, but becomes unnecessary when the child becomes the root. hence -1
                    ans[neighbour.v] = ans[node] - 1; //parent node had paid extra +1 to come to v via reverse edge, hence we need to reduce it by 1 to go from v to node via original edge
                }

                dfsReroot(neighbour.v, node, ans, graph);
            }
        }
    }

    static class Edge{
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
