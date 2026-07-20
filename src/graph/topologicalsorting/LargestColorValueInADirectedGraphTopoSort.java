package graph.topologicalsorting;

import java.util.*;

public class LargestColorValueInADirectedGraphTopoSort {

    //https://leetcode.com/problems/largest-color-value-in-a-directed-graph/

    //important : this can be solved via both DFS and topo sort logic

    /*
    Comparing the two

    DFS + Memoization

    child
      ↑
    parent

    DP flows upward.
    Need recursion.
    Need cycle detection with recursion stack.
    Think in post-order.

    Topo + DP

    parent
       ↓
    child

    DP flows forward.
    Iterative.
    Cycle detection comes for free with Kahn's algorithm.
    Easier to reason about dependencies.
     */

    /*
    Approach: Topological Sort + DP

    1. Build the graph and compute the indegree of every node.

    2. Initialize the queue with all source nodes (indegree == 0).
       Each source starts with one occurrence of its own color.

    3. DP State:
       dp[node][c] = Maximum occurrences of color 'c'
                     on any path ending at 'node'.

    4. Process nodes in topological order.
       - Propagate DP from the current node to each neighbour:
           dp[neighbour][c] = max(dp[neighbour][c], dp[curr][c])
       - Decrement the neighbour's indegree.
       - When all of its parents have been processed (indegree becomes 0):
           - Add the neighbour's own color.
           - Push it into the queue.

    5. If all nodes are not processed, a cycle exists, so return -1.

    6. The answer is the maximum value in the DP table.

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

        int[] indegree = new int[n];

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);

            indegree[v]++;
        }

        Queue<Integer> bfsQueue = new ArrayDeque<>();
        int[][] dp = new int[n][26];

        for(int i=0; i<n; i++){
            if(indegree[i] == 0){
                bfsQueue.offer(i);
                dp[i][colors.charAt(i) - 'a']++;
            }
        }

        int nodesProcessed = 0;

        while(!bfsQueue.isEmpty()){

            int curr = bfsQueue.poll();
            nodesProcessed++;

            //explore neighbours
            for(int neighbour : graph.get(curr)){

                //propagate color to the neighbour
                for(int i=0; i<26; i++){
                    dp[neighbour][i] = Math.max(dp[neighbour][i], dp[curr][i]);
                }

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.offer(neighbour);
                    dp[neighbour][colors.charAt(neighbour) - 'a']++; // Increment only when indegree becomes 0 to avoid counting the node's color multiple times
                }
            }
        }

        if(nodesProcessed != n){
            return -1; //cycle detected
        }

        //find answer
        int answer = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<26; j++){
                answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer;
    }
}
