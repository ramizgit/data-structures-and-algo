package graph.topologicalsorting.topologicalDp;

import java.util.*;

/*
A game has n levels, connected by m teleporters, and your task is to get from level 1 to level n. The game has been designed so that there are no directed cycles in the underlying graph. In how many ways can you complete the game?
Input
The first input line has two integers n and m: the number of levels and teleporters. The levels are numbered 1,2,\dots,n.
After this, there are m lines describing the teleporters. Each line has two integers a and b: there is a teleporter from level a to level b.
Output
Print one integer: the number of ways you can complete the game. Since the result may be large, print it modulo 10^9+7.
Constraints

1 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
4 5
1 2
2 4
1 3
3 4
1 4

Output:
3
 */

public class GameRoutes {

    //https://cses.fi/problemset/task/1681

    static final int MOD = 1_000_000_007;

    // Time : O(V + E)
    // Space: O(V + E)
    public int findNumOfWaysToCompleteTheGame(int n, int[][] teleporters)
    {
        //build graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n+1]; //initialize indegree as zero for all nodes

        //populate graph and indegree as per prerequisites
        for(int[] teleporter : teleporters){
            int u = teleporter[0];
            int v = teleporter[1];

            graph.get(u).add(v); //each flight is a one-way flight

            indegree[v]++;
        }

        Queue<Integer> topoQueue = new ArrayDeque<>();

        for(int node=1; node<=n; node++){
            if(indegree[node] == 0){
                topoQueue.offer(node);
            }
        }

        int[] dp = new int[n+1]; // dp[u] = number of ways to reach node u from node 1, start with 0 for all
        dp[1] = 1; //starting node

        while(!topoQueue.isEmpty()){

            int curr = topoQueue.poll();

            //explore neighbours
            for(int neighbour : graph.get(curr)){

                //state propagation from parent to neighbouring child nodes
                dp[neighbour] = (dp[neighbour] + dp[curr]) % MOD;

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    topoQueue.offer(neighbour);
                }
            }
        }

        return dp[n];
    }
}
