package consistenthashing.graph.mst;

import java.util.*;

public class MinCostToConnectAllPoints {

    //https://leetcode.com/problems/min-cost-to-connect-all-points/description/

    /*
     * Each node is added to the MST exactly once.
     * For every node added, we iterate over all n points.
     * Hence there are O(n²) Manhattan distance computations.
     *
     * Every candidate edge is pushed into the priority queue.
     * Total heap insertions = O(n²).
     *
     * Each heap insertion/removal costs O(log n).
     *
     * Overall:
     * O(n² × log n²) ~ O(n² × log n)
     *
     * For optimal O(n^2) follow MinCostToConnectAllPointsV2
     */
    public int minCostConnectPoints(int[][] points)
    {
        //input validation
        if(points == null || points.length == 0 || points[0].length == 0){
            return -1;
        }

        int n = points.length;

        //prims algorithm
        //minheap
        PriorityQueue<State> minheap = new PriorityQueue<>( (a, b) -> Integer.compare(a.cost, b.cost) ); //always process node with min cost first
        minheap.offer(new State(0, 0)); //pick first point as stating point with cost 0

        boolean[] visited = new boolean[n];
        int totalCost = 0;
        int nodeCount = 0;

        while(!minheap.isEmpty()){

            State curr = minheap.poll(); //Each heap operation costs O(log n)

            //visited check
            if(visited[curr.node]){
                continue;
            }

            // add node to MST
            visited[curr.node] = true;
            totalCost += curr.cost;
            nodeCount++;

            //early exit
            if(nodeCount == n){
                return totalCost;
            }

            //explore neighbours
            //the graph is implicit and complete, so every unvisited point is a neighboring vertex
            for(int neighbour=0; neighbour<n; neighbour++){
                if(!visited[neighbour]){
                    //manhattan distance
                    int edgeCost = Math.abs(points[curr.node][0] - points[neighbour][0]) + Math.abs(points[curr.node][1] - points[neighbour][1]);
                    minheap.offer(new State(neighbour, edgeCost)); //enqueue O(log n)
                }
            }
        }

        return -1;
    }

    static class State{
        int node;
        int cost;

        public State(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

}
