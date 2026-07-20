package graph.topologicalsorting;

import java.util.*;

public class AllAncestorsOfANodeInDAG {

    //https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/description/

    public List<List<Integer>> getAncestors(int n, int[][] edges)
    {
        //build graph ad adj. list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n];

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);

            indegree[v]++;
        }

        Map<Integer, Set<Integer>> nodeToParents = new HashMap<>();

        //add all 0 degree nodes to bfs queue
        Queue<Integer> bfsQueue = new ArrayDeque<>();

        for(int i=0; i<n; i++){
            if(indegree[i] == 0){
                bfsQueue.offer(i);
                nodeToParents.put(i, Collections.emptySet());
            }
        }

        while(!bfsQueue.isEmpty()){

            int curr = bfsQueue.poll();

            //explore neighbours
            for(int neighbour : graph.get(curr)){

                //process ancestors
                nodeToParents.computeIfAbsent(neighbour, key -> new HashSet<>()).addAll(nodeToParents.get(curr)); //add grand parents
                nodeToParents.get(neighbour).add(curr); //add parent

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.offer(neighbour);
                }
            }
        }

        //collect result
        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<n; i++){
            List<Integer> ancestors = new ArrayList<>(nodeToParents.get(i));
            Collections.sort(ancestors);
            result.add(ancestors);
        }

        return result;
    }
}
