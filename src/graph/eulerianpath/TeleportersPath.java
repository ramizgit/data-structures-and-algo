package graph.eulerianpath;

import java.util.*;

/*
A game has n levels and m teleportes between them. You win the game if you move from level 1 to level n using every teleporter exactly once.
Can you win the game, and what is a possible way to do it?
Input
The first input line has two integers n and m: the number of levels and teleporters. The levels are numbered 1,2,\dots,n.
Then, there are m lines describing the teleporters. Each line has two integers a and b: there is a teleporter from level a to level b.
You can assume that each pair (a,b) in the input is distinct.
Output
Print m+1 integers: the sequence in which you visit the levels during the game. You can print any valid solution.
If there are no solutions, print "IMPOSSIBLE".
Constraints

2 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
5 6
1 2
1 3
2 4
2 5
3 1
4 2

Output:
1 3 1 2 4 2 5
 */

public class TeleportersPath {

    //https://cses.fi/problemset/task/1693

    public List<Integer> findEulerPathInDirectedGraph(int n, int m, int[][] paths)
    {
        //build graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];

        //populate edges
        for(int[] path : paths){
            int u = path[0];
            int v = path[1];

            graph.get(u).add(v);

            indegree[v]++;
            outdegree[u]++;
        }

        //verify Euler path conditions using indegree and outdegree.
        for (int i = 1; i <= n; i++) {
            if(i == 1){
                if(outdegree[i] != indegree[i] + 1){
                    return Collections.emptyList(); // IMPOSSIBLE
                }
            }else if(i == n){
                if(indegree[i] != outdegree[i] + 1){
                    return Collections.emptyList(); // IMPOSSIBLE
                }
            }else{
                if(outdegree[i] != indegree[i]){
                    return Collections.emptyList(); // IMPOSSIBLE
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        dfs(1, graph, path);

        Collections.reverse(path);

        if (path.size() != m + 1) {
            return Collections.emptyList();
        }

        if (path.getFirst() != 1 || path.getLast() != n) {
            return Collections.emptyList();
        }

        return path;
    }

    //post oder dfs - Hierholzer algo
    private void dfs(int node, Map<Integer, List<Integer>> graph, List<Integer> path)
    {
        //explore neighbour
        List<Integer> neighbours = graph.get(node);

        while (!neighbours.isEmpty()){
            int neighbour = neighbours.removeLast(); //this is the key Hierholzer step - We're marking edge used by removing it from the graph
            dfs(neighbour, graph, path);
        }

        path.add(node);
    }
}
