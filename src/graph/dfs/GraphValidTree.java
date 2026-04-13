package graph.dfs;

import java.util.*;

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

        //initialize graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph based on input edges
        for(int[] edge : edges){
            int src = edge[0];
            int des = edge[1];

            //add both edges since its undirected graph
            graph.get(src).add(des);
            graph.get(des).add(src);
        }

        //dfs logic
        Set<Integer> visited = new HashSet<>();
        if(hasCycle(0, -1, graph, visited)){
            return false;
        }

        return visited.size() == n;
    }

    //dfs to detect cycle in undirected graph
    public boolean hasCycle(int start, int parent, Map<Integer, List<Integer>> graph, Set<Integer> visited)
    {
        visited.add(start);

        //expore all neighborus
        for(int neighbour : graph.get(start)){
            if(!visited.contains(neighbour)){
                if(hasCycle(neighbour, start, graph, visited)){
                    return true;
                }
            }else if(neighbour != parent){
                return true;
            }
        }

        return false;
    }
}
