package graph;

import java.util.*;

public class GraphValidTree {
    //https://neetcode.io/problems/valid-tree?list=blind75

    /*
    criteria:
    1. no loop
    2. all vertices connected as one component
    3. a tree with n nodes must have exactly n - 1 edges
     */

    public static void main(String[] args)
    {
        int[][] input = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(validTree(5, input)); //true

        int[][] input2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(validTree(5, input2)); //false
    }

    private static boolean validTree(int n, int[][] edges)
    {
        if (edges.length != n - 1){
            return false; //a tree with n nodes must have exactly n - 1 edges.
        }

        //initailize graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            //populate both side since undirected graph
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        //run dfs to detect cycle as well check one single component in the graph
        Set<Integer> visited = new HashSet<>();
        if(hasCycle(0, -1, graph, visited)){
            return false;
        }

        //if visited is same size as graph vertices, then all componts are reachable from 0, hence connected
        return visited.size() == graph.size();
    }

    //THIS LOGIC IS GENERIC TO DETECH CYCLE IN ANY UNDIRECTED GRAPH
    private static boolean hasCycle(int start, int parent, Map<Integer, List<Integer>> graph, Set<Integer> visited)
    {
        visited.add(start);

        //explore neighbours
        List<Integer> neighbours = graph.get(start);
        for(int neighbour : neighbours){
            if(!visited.contains(neighbour)){
                if(hasCycle(neighbour, start, graph, visited)){
                    return true;
                }
            }else if(neighbour != parent){
                return true;
            }
        }
        return false;
    }
}

/*
If you start DFS at 0:

From 0, you go to 1

From 1, you see neighbor 0

Since 0 is already visited, you might think “cycle!”

But that’s wrong — it’s just the edge back to parent, not an actual cycle.
 */
