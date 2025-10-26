package graph;

import java.util.*;

public class ReorderRoutes {
    public static void main(String[] args)
    {
        int[][] input = { {0,1},{1,3},{2,3},{4,0},{4,5} };
        System.out.println(minReorder(6, input));
    }

    private static int minReorder(int n, int[][] connections)
    {
        //build graph as well as neighbours
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, List<Integer>> neighbours = new HashMap<>();

        for(int[] con : connections){
            int src = con[0];
            int des = con[1];

            //graph
            graph.computeIfAbsent(src, k->new ArrayList<>()).add(des);

            //neighbour
            neighbours.computeIfAbsent(src, k->new ArrayList<>()).add(des);
            neighbours.computeIfAbsent(des, k->new ArrayList<>()).add(src);
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
                if(graph.getOrDefault(src, Collections.emptyList()).contains(neighbour)){
                    result[0]++; // count only when exploring forward
                }
                dfs(graph, neighbours, neighbour, visited, result);
            }
        }
    }
}
