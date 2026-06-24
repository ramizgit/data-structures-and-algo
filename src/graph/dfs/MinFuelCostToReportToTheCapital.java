package consistenthashing.graph.dfs;

import java.util.*;

public class MinFuelCostToReportToTheCapital {

    //https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/description/

    public long minimumFuelCost(int[][] roads, int seats)
    {
        /*
        Postorder DFS:
        - Each city contributes one representative.
        - subtreeSize[node] = total representatives in its subtree.
        - To move representatives from a child subtree to its parent,
          we need ceil(subtreeSize[child] / seats) cars.
        - Each car crossing one edge costs 1 fuel.
        - Sum the fuel contribution of every non-root subtree.
        */

        //input validation
        if(roads == null || roads.length == 0){
            return 0;
        }

        int n = roads.length + 1; //since n-1 edges

        //initialize graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] road : roads){
            int u = road[0];
            int v = road[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] subtreeSize = new int[n];
        long[] fuel = new long[1];

        dfs(0, -1, graph, seats, subtreeSize, fuel);

        return fuel[0];
    }

    //postorder DFS
    private void dfs(int node, int parent, Map<Integer, List<Integer>> graph, int seats, int[] subtreeSize, long[] fuel)
    {
        subtreeSize[node] = 1; // every city has one representative

        //explore neighbours
        for(int neighbour : graph.get(node)){

            if(neighbour == parent){
                continue;
            }

            dfs(neighbour, node, graph, seats, subtreeSize, fuel);


            fuel[0] += (long) Math.ceil((double) subtreeSize[neighbour] / seats); // every representative from child subtree must cross edge (child -> node)
            //fuel[0] += (subtreeSize[neighbour] + seats - 1) / seats; // could be written like this as well

            subtreeSize[node] += subtreeSize[neighbour]; // add child subtree representatives

            /*
            Important note :-
            subtreeSize[child] = number of people crossing edge (child -> parent)
            That's why we use:
            fuel += ceil(subtreeSize[child] / seats);
            and not:
            fuel += ceil(subtreeSize[parent] / seats);
             */
        }
    }
}
