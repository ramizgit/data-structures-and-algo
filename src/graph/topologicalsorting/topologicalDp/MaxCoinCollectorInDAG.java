package graph.topologicalsorting.topologicalDp;

import java.util.*;

public class MaxCoinCollectorInDAG {

    //this is diff. from graph.scc.CoinCollector (which can have cycle), here this is a DAG

    /*
    DAG
     ↓
    Topological Sort
     ↓
    DP on the DAG
     ↓
    Maximum coins
     */

    /*
    why topo:
    in topological sort, we start with indegree-0 nodes because they have no prerequisites/dependencies. Therefore, their DP values can be finalized immediately.
     */

    public int collectMaxCoin(int n, int[][] edges, int[] coins)
    {
        //initialize graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n];

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v); //u -> v directed edge

            indegree[v]++;
        }

        Queue<Integer> topoQueue = new ArrayDeque<>();
        int[] dp = new int[n]; //dp[i] = max coin collected while visiting ith node

        for(int i=0; i<n; i++){
            if(indegree[i] == 0){
                topoQueue.offer(i);
                dp[i] = coins[i]; //base case for 0 indegree starting nodes
            }
        }

        while(!topoQueue.isEmpty()){

            int curr = topoQueue.poll();

            //explore neighbours
            for(int neighbour : graph.get(curr)){

                dp[neighbour] = Math.max(
                        dp[neighbour], //best path found so far
                        dp[curr] + coins[neighbour] //best path through curr + neighbour's coins
                );

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    topoQueue.offer(neighbour);
                }
            }
        }

        int maxCoin = 0;

        for (int c : dp) {
            maxCoin = Math.max(maxCoin, c);
        }

        return maxCoin;
    }
}
