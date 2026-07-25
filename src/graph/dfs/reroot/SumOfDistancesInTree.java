package graph.dfs.reroot;

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

        //build graph as adjacency list - O(n)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges - O(n)
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            //add both undirected edges
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        //first dfs to compute subtree sizes and dist[0]
        int[] size = new int[n];
        int[] answer = new int[n]; //answer array
        dfsComputeSubtreeInfo(0, -1, 0, size, answer, graph); //O(n)

        //second dfs to compute answer for other nodes
        dfsReroot(0, -1, size, answer, graph, n); //O(n)

        return answer;
    }

    //second dfs to compute answer for all other nodes
    private void dfsReroot(int node, int parent, int[] size, int[] dist, Map<Integer, List<Integer>> graph, int n)
    {
        for(int neighbour : graph.get(node)){
            if(neighbour == parent){
                continue;
            }

            /*
            When moving root from parent -> child

            Nodes inside child's subtree:
            distance decreases by 1
            contribution = -size[child]

            Nodes outside child's subtree:
            distance increases by 1
            contribution = +(n - size[child])
            */

            dist[neighbour] = dist[node] // reuse distance sum of parent node
                    - size[neighbour] // number of nodes that become 1 step closer when rerooting to this neighbour node
                    + (n - size[neighbour]); // all other nodes become 1 step farther

            dfsReroot(neighbour, node, size, dist, graph, n);
        }
    }

    //first dfs to get subtree size of all nodes, and distance for 0th node
    private void dfsComputeSubtreeInfo(int node, int parent, int depth, int[] size, int[] answer, Map<Integer, List<Integer>> graph)
    {
        size[node] = 1; //size of the node itself
        answer[0] += depth; // depth == distance from root node 0

        //explore neighbours
        for(int neighbour : graph.get(node)) {
            if(neighbour == parent){
                continue;
            }

            dfsComputeSubtreeInfo(neighbour, node, depth + 1, size, answer, graph);

            size[node] += size[neighbour]; //add the size of the child subtree
        }
    }
}
