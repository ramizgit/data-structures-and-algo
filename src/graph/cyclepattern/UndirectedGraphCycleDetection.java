package graph.cyclepattern;

import java.util.*;

public class UndirectedGraphCycleDetection {

    /*
    Important node :
    For undirected graph, both DFS and DSU method can detect cycle.
    This class explains DFS method.
    For DSU, refer GraphValidTree class.
     */

    public boolean hasCycle(int n, int[][] edges)
    {
        //initialize graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            //add both edges since undirected graph
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        //dfs
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++){
            if(!visited[i]){
                if(dfs(i, -1, visited, graph)){
                    return true;
                }
            }
        }

        return false;
    }

    //dfs to detect cycle in "undirected graph"
    public boolean dfs(int start, int parent, boolean[] visited, Map<Integer, List<Integer>> graph)
    {
        visited[start] = true; //mark visited

        //explore all neighbours
        for(int neighbour : graph.get(start)){
            if(!visited[neighbour]){
                if(dfs(neighbour, start, visited, graph)){
                    return true;
                }
            }else if(neighbour != parent){
                return true; //neighbour is not parent, cycle found
            }
        }

        return false;
    }
}
