package consistenthashing.grid.binarysearchongrid;

import java.util.*;

public class MinimizeTheMaximumEdgeWeightOfGraph {

    //https://leetcode.com/problems/minimize-the-maximum-edge-weight-of-graph/description/

    /*
    Time:
    Build graph: O(E)
    Binary search: O(log(maxWeight))
    BFS check: O(V + E)

    Total:
    O((V + E) * log(maxWeight))

    Space:
    O(V + E)
    */
    public int minMaxWeight(int n, int[][] edges, int threshold)
    {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        //build reverse graph to check reachability from 0 node to all other nodes
        Map<Integer, List<Edge>> revGraph = new HashMap<>();
        for(int i=0; i<n; i++){
            revGraph.put(i, new ArrayList<>());
        }

        //populate reverse edges
        for(int[] edge : edges){ //O(E)
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            revGraph.get(v).add(new Edge(u, w)); //v -> u reverse directed edge

            //keep track of min and max edge weight to be used in binary search
            min = Math.min(min, w);
            max = Math.max(max, w);
        }

        //binary search logic
        int low = min; // smallest possible maximum edge weight
        int high = max; // largest possible maximum edge weight
        int answer = -1;

        while(low <= high){

            int mid = low + (high - low)/2; //mid is a candidate maximum allowed edge weight.

            if(canReachAllNodes(n, revGraph, mid)){
                answer = mid; //possible answer
                high = mid -1; //try an even smaller maximum weight, as we can build graph with edges weight ≤ mid
            }else{
                low = mid + 1; //cant build graph with edges weight ≤ mid, try larger weight
            }
        }

        /*
        Important : Where are you checking threshold?

        threshold is not explicitly used.
        If all nodes can reach 0, we can keep only one outgoing edge per node,
        which automatically satisfies threshold >= 1.
         */

        return answer;
    }

    // check if we can build a valid graph using only edges whose weight ≤ maxWeightAllowed
    private boolean canReachAllNodes(int n, Map<Integer, List<Edge>> revGraph, int maxWeightAllowed)
    {
        //bfs logic
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(0); //starting node 0

        boolean[] visited = new boolean[n];
        visited[0] = true;

        int visitedCount = 0;

        while(!bfsQueue.isEmpty()){

            int node = bfsQueue.poll();

            visitedCount++; //process node

            //explore neighbours
            for(Edge neighbour : revGraph.get(node)){
                if(!visited[neighbour.v] && neighbour.w <= maxWeightAllowed){
                    bfsQueue.offer(neighbour.v);
                    visited[neighbour.v] = true;
                }
            }
        }

        return visitedCount == n;
    }

    class Edge{
        int v;
        int w;

        public Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
}
