package graph.dfs;

import java.util.*;

public class NumberOfConnectedComponents {

    private static int countConnectedComponents(int n, int[][] edges)
    {
        //initialize graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); //since undirected graph, hence adding both ways
        }

        //dfs to count connected componets
        boolean[] visited = new boolean[n];
        int connectedComponents = 0;

        for(int i=0; i<n; i++){
            if(!visited[i]){
                connectedComponents++;
                dfs(i, graph, visited);
            }
        }

        return connectedComponents;
    }

    private static void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited)
    {
        visited[node] = true;

        //explore neighbours
        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                dfs(neighbour, graph, visited);
            }
        }
    }
}
