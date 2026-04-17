package graph.dfs;

import java.util.*;

public class ReorderRoutes {

    //https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/

    public static void main(String[] args)
    {
        int[][] input = { {0,1},{1,3},{2,3},{4,0},{4,5} };
        System.out.println(minReorder(6, input));
    }

    private static int minReorder(int n, int[][] connections)
    {
        //initialize graph as well as undirected neighbours graph
        Map<Integer, List<Integer>> graph = new HashMap<>(); //acutal graph
        Map<Integer, List<Integer>> neighbours = new HashMap<>(); //undirected graph representation

        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
            neighbours.put(i, new ArrayList<>());
        }

        //populate graph as input connections
        for(int[] con : connections){
            int src = con[0];
            int des = con[1];

            //actual graph
            graph.get(src).add(des);

            //neighbour
            neighbours.get(src).add(des);
            neighbours.get(des).add(src);
        }

        //dfs
        int[] result = new int[1];
        dfs(graph, neighbours, 0, new HashSet<>(), result);

        return result[0];
    }

    private static void dfs(Map<Integer, List<Integer>> graph, Map<Integer, List<Integer>> neighbours, int src, Set<Integer> visited, int[] result)
    {
        visited.add(src);

        //explore neighbours
        for(int neighbour : neighbours.get(src)){
            //check if path exits fromm neighbour to this src
            if(!visited.contains(neighbour)){
                if(graph.get(src).contains(neighbour)){
                    result[0]++; // count only when exploring forward
                }
                dfs(graph, neighbours, neighbour, visited, result);
            }
        }
    }
}
