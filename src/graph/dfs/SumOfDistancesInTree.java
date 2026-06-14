package consistenthashing.graph.dfs;

import java.util.*;

public class SumOfDistancesInTree {

    //https://leetcode.com/problems/sum-of-distances-in-tree/description/

    /*
    There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
    You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
    Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
     */

    /*
    Time Complexity
    Build graph: O(n)
    DFS #1: O(n)
    DFS #2: O(n)

    Total: O(n)
     */
    public int[] sumOfDistancesInTree(int n, int[][] edges)
    {
        /*
        brute force : DFS from each node individually and compute distance, but O(n^2)
        optimal : DFS from one node, then reuse it, O(n)
         */

        //initialize graph - O(n)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph - O(n)
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            //add both undirected edges
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        //first dfs to compute subtree sizes and dist[0]
        int[] size = new int[n];
        int[] dist = new int[n];
        dfsComputeSubTreeSize(0, 0, size, dist, new boolean[n], graph); //O(n)

        //second dfs to compute answer for other nodes
        dfsReroot(0, size, dist, new boolean[n], graph, n); //O(n)

        return dist;
    }

    //second dfs to compute answer for all other nodes
    private void dfsReroot(int node, int[] size, int[] dist, boolean[] visited, Map<Integer, List<Integer>> graph, int n)
    {
        visited[node] = true;

        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){

                dist[neighbour] = dist[node] // reuse the distance of parent node
                        - size[neighbour] // nodes inside neighbour's subtree become 1 step closer
                        + (n - size[neighbour]); // all other nodes become 1 step farther

                dfsReroot(neighbour, size, dist, visited, graph, n);
            }
        }
    }

    //first dfs to get subtree size of all nodes, and distance for 0th node
    private void dfsComputeSubTreeSize(int node, int depth, int[] size, int[] dist, boolean[] visited, Map<Integer, List<Integer>> graph)
    {
        visited[node] = true;
        size[node] = 1;
        dist[0] += depth; // depth == distance from root node 0

        //explore neighbours
        for(int neighbour : graph.get(node)) {
            if(!visited[neighbour]){
                dfsComputeSubTreeSize(neighbour, depth + 1, size, dist, visited, graph);
                size[node] += size[neighbour];
            }
        }
    }
}
