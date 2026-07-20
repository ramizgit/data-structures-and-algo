package graph.dfs;

import java.util.*;

public class LargestColorValueInADirectedGraph {

    //https://leetcode.com/problems/largest-color-value-in-a-directed-graph/description/

    //important : this can be solved via both DFS and topo sort logic

    /*
    Approach: DFS + Memoization (DP on DAG)

    1. Build the graph as an adjacency list.

    2. Run DFS from every unvisited node.
       - Use 'visited' to avoid recomputing a node.
       - Use 'inCurrPath' to detect cycles.
       - If a cycle is found, return -1.

    3. DP State:
       dp[node][c] = Maximum occurrences of color 'c'
                     on any path starting from 'node'.

    4. DFS (Post-order):
       - First process all children.
       - Merge each child's DP into the current node:
           dp[node][c] = max(dp[node][c], dp[child][c])
       - Finally increment the count of the current node's color.

    5. The answer is the maximum value in dp[node] across all connected components.

    Time : O((V + E) * 26) ≈ O(V + E)
    Space: O(V * 26 + V + E) ≈ O(V + E)
    */

    public int largestPathValue(String colors, int[][] edges)
    {
        int n = colors.length();

        //build graph ad adj. list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
        }

        int[][] dp = new int[n][26];
        boolean[] visited = new boolean[n];
        boolean[] inCurrPath = new boolean[n];

        int answer = -1;

        for(int i=0; i<n; i++){
            if(!visited[i]){
                answer = Math.max(answer, dfs(i, graph, visited, inCurrPath, dp, colors));
            }
        }

        return answer;
    }

    private int dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] inCurrPath, int[][] dp, String colors)
    {
        visited[node] = true;
        inCurrPath[node] = true;

        //explore neighbours
        for(int neighbour : graph.get(node)){

            if(!visited[neighbour]){
                if(dfs(neighbour, graph, visited, inCurrPath, dp, colors) == -1){
                    //cycle detected
                    return -1;
                }
            }else if(inCurrPath[neighbour]){
                //cycle detected
                return -1;
            }

            // Merge child's DP into current node.
            // For every color, keep the best count among all outgoing paths.
            for (int c = 0; c < 26; c++) {
                dp[node][c] = Math.max(dp[node][c], dp[neighbour][c]);
            }
        }

        // Include current node's own color AFTER merging children
        dp[node][colors.charAt(node) - 'a']++;

        inCurrPath[node] = false;

        // Return the maximum color frequency on any path starting from this node
        int max = 0;

        for (int c = 0; c < 26; c++) {
            max = Math.max(max, dp[node][c]);
        }

        return max;
    }
}
