package consistenthashing.graph.dfs;

import java.util.*;

public class MinScoreOfAPathBetweenTwoCitiesViaDfs {

    //https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/

    //IMPORTANT : Not a dijkstra/bin search problem since revisiting cities/roads is allowed, its a simpel DFS/DSU problem to find min edge

    /*
    Key Observation:
    - Score of a path = minimum edge weight on that path.
    - We need the minimum possible score between city 1 and city n.
    - *** "Since revisiting cities/roads is allowed, we can always detour through any edge
      in the connected component containing city 1 before reaching city n."
    - Therefore, the answer is simply the minimum edge weight in the connected component
      containing city 1.

    Why not Dijkstra / Binary Search?
    - This is not a shortest-path or bottleneck-path problem.
    - Dijkstra is unnecessary because we are not optimizing over individual paths;
      any edge in the connected component can be included in a valid path.
    - Binary Search is unnecessary because the answer can be obtained directly by
      traversing the connected component and tracking the minimum edge weight.

    Approach:
    - Build the graph.
    - DFS/BFS from city 1.
    - For every reachable edge, update the global minimum edge weight.
    - Return the minimum edge found.
    */

    public int minScore(int n, int[][] roads)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] road : roads){
            int a = road[0];
            int b = road[1];
            int distance = road[2];

            //add both direction since bidirectional roads
            graph.get(a).add(new Edge(b, distance));
            graph.get(b).add(new Edge(a, distance));
        }

        //dfs from source and track min edge distance
        boolean[] visited = new boolean[n+1];
        int[] minDistance = new int[1];
        minDistance[0] = Integer.MAX_VALUE;

        dfs(1, visited, graph, minDistance);

        return minDistance[0];
    }

    private void dfs(int node, boolean[] visited, Map<Integer, List<Edge>> graph, int[] minDistance)
    {
        visited[node] = true;

        //explore neighbours
        for(Edge neighbour : graph.get(node)){

            minDistance[0] = Math.min(minDistance[0], neighbour.distance); //must consider all edges, including back/cross edges, hence this is outside of visited check

            if(!visited[neighbour.v]){
                dfs(neighbour.v, visited, graph, minDistance);
            }
        }
    }

    static class Edge{
        int v;
        int distance;

        public Edge(int v, int distance){
            this.v = v;
            this.distance = distance;
        }
    }
}
