package consistenthashing.graph.bfs;

import java.util.*;

public class ShortestPathWithAlternatingColors {

    //https://leetcode.com/problems/shortest-path-with-alternating-colors/description/

    /*
    Approach:
    - Use BFS since we need shortest paths in an unweighted graph.
    - BFS state = (node, incoming edge color, distance).
    - Color is part of the state because reaching the same node via different colors
      can lead to different valid next moves.
    - Start BFS from node 0 with both colors, allowing either color as the first move.
    - Only traverse neighbors with a different color than the current state's color.
    - The first time a node is dequeued, its shortest alternating path distance is found.
    */

    // Time Complexity : O(V + E)
    // Space Complexity: O(V + E)
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges)
    {
        //initialize graph as adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] redEdge : redEdges){
            int u = redEdge[0];
            int v = redEdge[1];

            graph.get(u).add(new Edge(v, 0)); //0 is for RED
        }

        for(int[] blueEdge : blueEdges){
            int u = blueEdge[0];
            int v = blueEdge[1];

            graph.get(u).add(new Edge(v, 1)); //1 is for BLUE
        }

        //bfs logic
        Queue<State> bfsQueue = new ArrayDeque<>();
        // trick : seed BFS with both colors since node 0 has no incoming edge, allowing either color as the first move.
        bfsQueue.offer(new State(0, 0, 0)); //starting node (0) with red color and distance 0
        bfsQueue.offer(new State(0, 1, 0)); //starting node (0) with blue color and distance 0

        boolean[][] visited = new boolean[n][2]; // visited[node][color] because reaching the same node via different colors represents different BFS states
        visited[0][0] = true;
        visited[0][1] = true;

        int[] result = new int[n];
        Arrays.fill(result, -1);

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            if(result[curr.node] == -1){
                result[curr.node] = curr.distance; //BFS guarantees the first time a node is dequeued is the shortest distance to that node.
            }

            //explore neighbours
            for(Edge neighbour : graph.get(curr.node)){

                //alternate color check
                if(neighbour.color == curr.color){
                    continue;
                }

                //visited check
                if(visited[neighbour.node][neighbour.color]){
                    continue;
                }

                visited[neighbour.node][neighbour.color] = true; //mark visited
                bfsQueue.offer(new State(neighbour.node, neighbour.color, curr.distance+1));
            }
        }

        return result;
    }

    static class Edge{
        int node;
        int color;

        public Edge(int node, int color){
            this.node = node;
            this.color = color;
        }
    }

    static class State{
        int node;
        int color;
        int distance;

        public State(int node, int color, int distance) {
            this.node = node;
            this.color = color;
            this.distance = distance;
        }
    }
}
