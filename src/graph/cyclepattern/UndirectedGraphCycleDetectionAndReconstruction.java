package graph.cyclepattern;

import java.util.*;

public class UndirectedGraphCycleDetectionAndReconstruction {

    public List<Integer> detectAndPrintCycle(int n, int[][] edges)
    {
        //build graph as adjacency list
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
        int[] parent = new int[n]; //parent tracking for cycle reconstruction
        Arrays.fill(parent, -1);
        int[] cycleStart = new int[1];
        int[] cycleEnd = new int[1];
        boolean cycleFound = false;

        for(int i=0; i<n; i++){
            if(!visited[i]){
                if(dfs(i, -1, visited, graph, parent, cycleStart, cycleEnd)){
                    cycleFound = true;
                    break;
                }
            }
        }

        if(!cycleFound){
            return Collections.emptyList();
        }

        //construct path
        List<Integer> cycle = new ArrayList<>();
        cycle.add(cycleStart[0]);
        int curr = cycleEnd[0];

        while(curr != cycleStart[0]){
            cycle.add(curr);
            curr = parent[curr];
        }

        cycle.add(cycleStart[0]);

        Collections.reverse(cycle);

        return cycle;
    }

    //dfs to detect cycle in "undirected graph"
    public boolean dfs(int node, int parent, boolean[] visited, Map<Integer, List<Integer>> graph, int[] parentArr, int[] cycleStart, int[] cycleEnd)
    {
        visited[node] = true; //mark visited

        //explore all neighbours
        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                parentArr[neighbour] = node;
                if(dfs(neighbour, node, visited, graph, parentArr, cycleStart, cycleEnd)){
                    return true;
                }
            }else if(neighbour != parent){
                cycleStart[0] = node;
                cycleEnd[0] = neighbour;
                return true; //neighbour is not parent, cycle found
            }
        }

        return false;
    }
}
