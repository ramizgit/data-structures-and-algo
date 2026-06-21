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

    /*
    Approach:-
    During DFS from city 0, we always move away from city 0. If the original road already points toward city 0 (a "friendly" edge),
    DFS will traverse the artificial reverse edge with cost 0, meaning no reversal is needed.
    If the original road points away from city 0 (an "unfriendly" edge), DFS will traverse the original edge with cost 1, meaning this road must be reversed.
    By summing the costs of all traversed edges, we get the minimum number of reversals.

    😊 Friendly edge = original road already points toward city 0 → DFS traverses the artificial reverse edge → cost = 0.
    😞 Unfriendly edge = original road points away from city 0 → DFS traverses the original edge → cost = 1.
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
            graph.get(v).add(new Edge(u, 0)); //artificial reverse edges with cost 0, correct direction (toward 0), hence cost 0
        }

        //run dfs from node 0
        int[] cost = new int[1];
        dfsRoot(0, -1, cost, graph);

        return cost[0];
    }

    //since graph is tree, hence using parent rather than visited array approach, this is more space efficient O(1)
    private void dfsRoot(int node, int parent, int[] cost, Map<Integer, List<Edge>> graph)
    {
        //explore neighborus
        for(Edge neighbour : graph.get(node)){
            if(neighbour.v != parent){ //ensures recursion only goes to children, not back to parent to avoid infinite loop
                cost[0] += neighbour.w;
                dfsRoot(neighbour.v, node, cost, graph);
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
