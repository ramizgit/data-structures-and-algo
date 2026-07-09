package graph.dfs;

import java.util.*;

public class NumOfCompleteComponents {

    /*
    Approach:
    1. Build an adjacency list.
    2. Perform DFS from every unvisited node to explore one connected component.
    3. During DFS, count:
       - number of vertices
       - sum of degrees
    4. Divide the degree count by 2 to obtain the number of edges.
    5. A component is complete iff:
          edges = vertices * (vertices - 1) / 2

    Time : O(V + E)
    Space: O(V + E)
    */

    public int countCompleteComponents(int n, int[][] edges)
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

            graph.get(u).add(v);
            graph.get(v).add(u); //since undirected graph, hence adding both ways
        }

        //dfs logic
        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for(int i=0; i<n; i++){
            if(!visited[i]){

                int[] component = new int[2]; // component[0] = number of nodes, component[1] = sum of degrees

                dfs(i, graph, visited, component);

                //check whether this connected component is complete.
                int numOfNodes = component[0];
                int numOfEdges = component[1] / 2; //divide by 2 as every edge is counted twice in an undirected graph dfs

                int expectedNumOfEdges = numOfNodes * (numOfNodes-1) / 2; //formula to get number of edges in a complete connected graph

                if(numOfEdges == expectedNumOfEdges){
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited, int[] component)
    {
        visited[node] = true;

        //capture num of nodes and sum of degrees of all nodes in the component.
        component[0]++; //number of nodes
        component[1] += graph.get(node).size(); //sum of degrees of each node, later to be divided by 2 since undirected graph

        //explore neighbours
        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                dfs(neighbour, graph, visited, component);
            }
        }
    }
}
