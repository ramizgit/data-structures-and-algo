package graph;

import java.util.*;

public class TopologicalSortingKahnAlgo {
    public static void main(String[] args)
    {
        System.out.println(topologicalSort(new int[][]{ {1,2}, {1,3}, {2,4}, {3,4}, {4,5} }));

        System.out.println(topologicalSort(new int[][]{ {1,3}, {1,4}, {2,3}, {4,5}, {5,2} }));

        System.out.println(topologicalSort(new int[][]{ {1,3}, {1,4}, {2,3}, {2,5}, {4,5} }));

    }

    private static List<Integer> topologicalSort(int[][] input)
    {
        Map<Integer, List<Integer>> graph = buildGraph(input);
        Map<Integer, Integer> indegree = indegree(graph);
        return bfs(graph, indegree);
    }

    private static List<Integer> bfs(Map<Integer, List<Integer>> graph, Map<Integer, Integer> indegree)
    {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        //add zero indegree vertices into the queue to start with
        Set<Integer> set = new HashSet<>(graph.keySet());
        set.removeAll(indegree.keySet());
        queue.addAll(set);

        while (!queue.isEmpty()){
            int poll = queue.poll();
            result.add(poll);

            //reduce indegree of all edges connected to poll
            for(int edge : graph.getOrDefault(poll, new ArrayList<>())){
                indegree.put(edge, indegree.get(edge) - 1);

                if(indegree.get(edge) == 0){
                    queue.add(edge);
                }
            }
        }

        return result;
    }

    private static Map<Integer, Integer> indegree(Map<Integer, List<Integer>> graph)
    {
        Map<Integer, Integer> indegreeMap = new HashMap<>();

        for(List<Integer> edges : graph.values()){
            for(int edge : edges){
                indegreeMap.put(edge, indegreeMap.getOrDefault(edge, 0)+1);
            }
        }
        return indegreeMap;
    }

    private static Map<Integer, List<Integer>> buildGraph(int[][] input)
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] ints : input) {
            graph.computeIfAbsent(ints[0], key -> new ArrayList<>()).add(ints[1]);
        }

        return graph;
    }
}
