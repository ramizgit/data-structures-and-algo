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
        Set<Integer> visited = new HashSet<>();
        int connectedComponents = 0;
        for(int i=0; i<n; i++){
            if(!visited.contains(i)){
                connectedComponents++;
                dfs(i, graph, visited);
            }
        }

        return connectedComponents;
    }

    private static void dfs(int start, Map<Integer, List<Integer>> graph, Set<Integer> visited)
    {
        visited.add(start);

        //explore neighbours
        for(int neighbour : graph.get(start)){
            if(!visited.contains(neighbour)){
                dfs(neighbour, graph, visited);
            }
        }
    }
}
