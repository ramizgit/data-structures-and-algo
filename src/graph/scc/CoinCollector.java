package graph.scc;

import java.util.*;

/*
A game has n rooms and m tunnels between them. Each room has a certain number of coins. What is the maximum number of coins you can collect while moving through the tunnels when you can freely choose your starting and ending room?
Input
The first input line has two integers n and m: the number of rooms and tunnels. The rooms are numbered 1,2,\dots,n.
Then, there are n integers k_1,k_2,\ldots,k_n: the number of coins in each room.
Finally, there are m lines describing the tunnels. Each line has two integers a and b: there is a tunnel from room a to room b. Each tunnel is a one-way tunnel.
Output
Print one integer: the maximum number of coins you can collect.
Constraints

1 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le k_i \le 10^9
1 \le a,b \le n

Example
Input:
4 4
4 5 2 7
1 2
2 1
1 3
2 4

Output:
16
 */

/*
Hint:-
DAG: DFS + memoization or topological-sort + DP [if its a DAG, then topo + DP, no need for scc]
Graph with cycles: SCC → condensed DAG → DP [which is this current problem as it can have cycles]
 */

public class CoinCollector {

    //https://cses.fi/problemset/task/1686

    //important : this is scc kosaraju + topological sort dp problem

    //todo : practice

    public int collectMaxCoin(int n, int[][] edges, int[] coins)
    {
        //Cycles → SCC compression → DAG → Topo DP

        /*
        Original Graph
          ↓
        Kosaraju
              ↓
        Component ID of every node
              ↓
        Build SCC DAG
              ↓
        Topo DP
         */

        //initialize graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v); //u -> v directed edge
        }

        //kosaraju algorithm

        // Step 1 : First pass post oder DFS in finish order, populating stack with nodes in decreasing order of finishing time
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfsFirstPass(i, visited, graph, stack);
            }
        }

        //Step 2 : Reverse graph
        //initialize reverse graph
        Map<Integer, List<Integer>> revGraph = new HashMap<>();
        for(int i=0; i<n; i++){
            revGraph.put(i, new ArrayList<>());
        }

        //populate reverse edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            revGraph.get(v).add(u); //v -> u reverse directed edge
        }

        //Step 3 : Second pass DFS in stack order
        Arrays.fill(visited, false); //reset visited array

        int[] sccCoin = new int[n];
        int[] sccComponents = new int[n];
        int componentId = 0;

        while(!stack.isEmpty()){

            int node = stack.pop();

            if(!visited[node]){
                dfsSecondPass(node, visited, revGraph, sccComponents, componentId, sccCoin, coins);
                componentId++;
            }
        }

        //build another DAG
        Map<Integer, Set<Integer>> dag = new HashMap<>();

        for (int i = 0; i < componentId; i++) {
            dag.put(i, new HashSet<>());
        }

        int[] indegree = new int[componentId];

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            int cu = sccComponents[u];
            int cv = sccComponents[v];

            if (cu != cv) {
                if(!dag.get(cu).contains(cv)){
                    dag.get(cu).add(cv);
                    indegree[cv]++;
                }
            }
        }

        Queue<Integer> bfsQueue = new ArrayDeque<>();
        int[] dp = new int[componentId];

        for (int i = 0; i < componentId; i++) {
            if(indegree[i] == 0){
                bfsQueue.offer(i);
                dp[i] = sccCoin[i];
            }
        }

        while(!bfsQueue.isEmpty()){

            int curr = bfsQueue.poll();

            //explore neighbours
            for(int neighbour : dag.get(curr)){

                //propagate coins from node to its neighbour
                dp[neighbour] = Math.max(dp[neighbour], //coins collected via existing best path
                        dp[curr] + sccCoin[neighbour] //coins collected via the current SCC path
                );

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.offer(neighbour);
                }
            }
        }

        int maxCoin = 0;

        for (int c : dp) {
            maxCoin = Math.max(maxCoin, c);
        }

        return maxCoin;
    }

    //postorder DFS
    private void dfsFirstPass(int node, boolean[] visited, Map<Integer, List<Integer>> graph, Deque<Integer> stack)
    {
        visited[node] = true; //mark visited

        //explore neighbours
        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                dfsFirstPass(neighbour, visited, graph, stack);
            }
        }

        stack.push(node); //push to stack after exploring all neighbours
    }

    //preorder DFS
    private void dfsSecondPass(int node, boolean[] visited, Map<Integer, List<Integer>> graph, int[] sccComponent, int componentId,
                               int[] sccCoin, int[] coins)
    {
        visited[node] = true; //mark visited
        sccComponent[node] = componentId; //assign current scc id to each node in the scc
        sccCoin[componentId] += coins[node]; //accumulate coins within current scc nodes

        //explore neighbours
        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                dfsSecondPass(neighbour, visited, graph, sccComponent, componentId, sccCoin, coins);
            }
        }
    }

}
