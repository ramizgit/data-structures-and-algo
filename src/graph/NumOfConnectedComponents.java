package graph;

import java.util.*;

public class NumOfConnectedComponents {

    public static void main(String[] args)
    {
        int[][] input = { {0,1},{0,2},{1,2},{3,4} };
        System.out.println(countCompleteComponents(6, input)); //3

        int[][] input2 = { {0,1},{0,2},{1,2},{3,4}, {3,5} };
        System.out.println(countCompleteComponents(6, input2)); //1
    }

    private static int countCompleteComponents(int n, int[][] edges)
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        //initialize graph
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); //since undirected graph, hence adding both ways
        }

        //dfs
        Set<Integer> visited = new HashSet<>();
        int connectedCompCount = 0;

        for(int i=0; i<n; i++){
            int[] output = new int[2];
            if(!visited.contains(i)){
                dfs(i, graph, visited, output);

                //check if connected component
                int numOfNodes = output[0];
                int numOfEdges = output[1] / 2;

                int expectedNumOfEdges = numOfNodes * (numOfNodes-1) / 2; //Note *** : Math formula, need to memorize

                if(numOfEdges == expectedNumOfEdges){
                    connectedCompCount++;
                }
            }
        }

        return connectedCompCount;
    }

    private static void dfs(int start, Map<Integer, List<Integer>> graph, Set<Integer> visited, int[] output)
    {
        visited.add(start);

        //capture num of nodes and edges in the output
        output[0]++; //number of nodes
        output[1] += graph.get(start).size(); //number of edges

        //explore neighbours
        for(int neighbour : graph.get(start)){
            if(!visited.contains(neighbour)){
                dfs(neighbour, graph, visited, output);
            }
        }
    }
}
