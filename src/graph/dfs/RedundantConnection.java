package graph.dfs;

import java.util.*;

public class RedundantConnection {

    //https://leetcode.com/problems/redundant-connection/

    /*
    Return an edge that can be removed so that the resulting graph is a tree of n nodes.
     */

    //Time complexity : O(E * (V + E))
    //Space complexity : O(V+E)
    public int[] findRedundantConnection(int[][] edges) {

        //initialize graph
        Map<Integer, List<Integer>> graph = new HashMap<>(); //undirected graph
        for(int[] edge : edges){
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
        }

        //add each edge one by one to find redundant
        for(int[] edge : edges){ //Time : O(E)
            int src = edge[0];
            int des = edge[1];

            // check redundancy before adding the edge
            Set<Integer> visited = new HashSet<>();
            if(hasPath(src, des, graph, visited)){ //Time : DFS takes O(V + E)
                return new int[]{src, des};
            }

            //add edge
            graph.get(src).add(des);
            graph.get(des).add(src);
        }

        return new int[0];
    }

    public boolean hasPath(int src, int des, Map<Integer, List<Integer>> graph, Set<Integer> visited)
    {
        if(src == des){
            return true;
        }

        visited.add(src);

        //explore all neighbours
        for(int neighbour : graph.get(src)){
            if(!visited.contains(neighbour)){
                if(hasPath(neighbour, des, graph, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}
