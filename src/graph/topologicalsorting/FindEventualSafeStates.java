package consistenthashing.graph.topologicalsorting;

import java.util.*;

public class FindEventualSafeStates {

    //https://leetcode.com/problems/find-eventual-safe-states/description/

    //Trick : Kahn's Topological Sort on the reversed graph.

    //Time : O(V + E + V log V)
    public List<Integer> eventualSafeNodes(int[][] graph)
    {
        /*
        Approach:-
        Reverse the graph.
        indegree actually stores indegree of the reversed graph (= outdegree of the original graph).
        Initialize queue with nodes having indegree == 0 (terminal nodes in original graph).
        Run Kahn's algorithm.
        Sort the result.
         */

        //input validation
        if(graph == null || graph.length == 0){
            return Collections.emptyList();
        }

        int n = graph.length;

        //initialize graph and indegree
        Map<Integer, List<Integer>> revGraph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        for(int i=0; i<n; i++){ //O(V)
            revGraph.put(i, new ArrayList<>());
            indegree.put(i, 0);
        }

        // build reversed graph.
        // indegree in reversed graph == outdegree in original graph.
        for(int i=0; i<n; i++){ //O(V)
            int[] neighbours = graph[i];
            for(int neighbour : neighbours){ //O(E)
                revGraph.get(neighbour).add(i); //rever order
                indegree.put(i, indegree.get(i) + 1);
            }
        }

        //kahn algo
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        for(int node : indegree.keySet()){
            if(indegree.get(node) == 0){
                bfsQueue.offer(node);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();

        //O(V+E) - Each node enters the queue once and Each edge is visited once
        while(!bfsQueue.isEmpty()){

            int curr = bfsQueue.poll();
            safeNodes.add(curr);

            //explore neighbours and add to bfs queue if indegree = 0
            for(int neighbour : revGraph.get(curr)){
                indegree.put(neighbour, indegree.get(neighbour) - 1);

                if(indegree.get(neighbour) == 0){
                    bfsQueue.offer(neighbour);
                }
            }
        }

        Collections.sort(safeNodes); //O(V log V)

        return safeNodes;
    }
}
