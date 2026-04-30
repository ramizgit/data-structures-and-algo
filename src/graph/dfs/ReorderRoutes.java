package graph.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReorderRoutes {

    //https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/

    /*
    Your task consists of reorienting some roads such that each city can visit the city 0.
     Return the minimum number of edges changed.
     */

    public int minReorder(int n, int[][] connections)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges as per inputs
        for(int[] edge : connections){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(new Edge(v, 1)); //original edges with cost 1, needs reversal, wrong direction (away from 0)
            graph.get(v).add(new Edge(u, 0)); //reverse edges with cost 0, correct direction (toward 0), hence cost 0
        }

        //run dfs from node 0
        int[] cost = new int[1];
        dfsRoot(0, -1, cost, graph);

        return cost[0];
    }

    private void dfsRoot(int node, int parent, int[] cost, Map<Integer, List<Edge>> graph)
    {
        //explore neighborus
        for(Edge neighbour : graph.get(node)){
            int v = neighbour.v;

            if(v != parent){ //ensures recursion only goes to children, not back to parent to avoid infinite loop
                cost[0] += neighbour.w;
                dfsRoot(v, node, cost, graph);
            }
        }
    }

    class Edge{
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
