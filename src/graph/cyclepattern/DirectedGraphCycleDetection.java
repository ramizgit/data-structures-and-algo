package graph.cyclepattern;

import java.util.*;

public class DirectedGraphCycleDetection {

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

            graph.get(u).add(v); //u -> v directed edge
        }

        //dfs
        boolean[] visited = new boolean[n];
        boolean[] inCurrPath = new boolean[n];

        for(int i=0; i<n; i++){
            if(!visited[i]){
                if(dfs(i, visited, inCurrPath, graph)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int node, boolean[] visited, boolean[] inCurrPath, Map<Integer, List<Integer>> graph)
    {
        //mark visited as well as in curr dfs path true
        visited[node] = true;
        inCurrPath[node] = true;

        //explore all neighbours
        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                if(dfs(neighbour, visited, inCurrPath, graph)){
                    return true;
                }
            }else if(inCurrPath[neighbour]){
                return true; //both visited and in curr path, cycle found
            }
        }

        inCurrPath[node] = false; //unmark in curr path for backtrack
        return false;
    }
}
