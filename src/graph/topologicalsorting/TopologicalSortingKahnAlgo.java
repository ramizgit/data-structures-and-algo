package graph;

import java.util.*;

public class TopologicalSortingKahnAlgo {

    public static void main(String[] args)
    {
        System.out.println(topologicalSort(new int[][]{ {1,2}, {1,3}, {2,4}, {3,4}, {4,5} }));
        System.out.println(topologicalSort(new int[][]{ {1,3}, {1,4}, {2,3}, {4,5}, {5,2} }));
        System.out.println(topologicalSort(new int[][]{ {1,3}, {1,4}, {2,3}, {2,5}, {4,5} }));
    }

    private static List<Integer> topologicalSort(int[][] edges)
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        // Build graph + indegree together
        for(int[] edge : edges){
            int src = edge[0], dest = edge[1];

            //build graph
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);
            graph.computeIfAbsent(dest, k -> new ArrayList<>());

            //track indegree
            indegree.put(dest, indegree.getOrDefault(dest, 0) + 1);
            indegree.putIfAbsent(src, 0); // ensure src also appears in indegree map
        }

        // Start with all 0-indegree nodes
        Queue<Integer> queue = new ArrayDeque<>();
        for(int key : indegree.keySet()){ //Time : O(V)
            if(indegree.get(key) == 0){
                queue.offer(key);
            }
        }

        List<Integer> result = new ArrayList<>();

        // Standard BFS
        while(!queue.isEmpty()){
            int node = queue.poll();
            result.add(node);

            for(int neighbor : graph.getOrDefault(node, new ArrayList<>())){
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if(indegree.get(neighbor) == 0){
                    queue.offer(neighbor);
                }
            }
        }

        // If result doesn't contain all nodes → cycle detected
        return (result.size() == graph.size()) ? result : new ArrayList<>();
    }
}
