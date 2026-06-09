package graph.dijkstra;

import java.util.*;

public class NetworkDelayTime {

    //https://leetcode.com/problems/network-delay-time/

    /*
    We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
    If it is impossible for all the n nodes to receive the signal, return -1.
     */

    public int networkDelayTime(int[][] times, int n, int k)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph as per input times
        for(int[] time : times){
            int u = time[0];
            int v = time[1];
            int t = time[2];

            graph.get(u).add(new Edge(v, t)); //directed edge u -> v (t)
        }

        //dijkstra algo
        PriorityQueue<State> minheap = new PriorityQueue<>( (a,b) -> a.dist - b.dist); //always process the node with the smallest known distance first
        minheap.offer(new State(k, 0)); //starting node

        int[] dist = new int[n+1]; //dist[i] = shortest time for node i to receive signal
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0; //starting node

        while(!minheap.isEmpty()){

            State curr = minheap.poll();

            //staleleness check
            if(curr.dist > dist[curr.node]){
                continue; //stale entry, ignore
            }

            //explore neighbours
            for(Edge neighbour : graph.get(curr.node)){

                int newDist = curr.dist + neighbour.time;

                if(newDist < dist[neighbour.node]){
                    dist[neighbour.node] = newDist;
                    minheap.offer(new State(neighbour.node, newDist));
                }
            }
        }

        //get the max time to reach any node, this will be the minimum time it takes for all the n nodes to receive the signal.
        int max = 0;

        for(int i=1; i<=n; i++){
            if(dist[i] == Integer.MAX_VALUE){
                return -1; //cant reach all nodes
            }
            max = Math.max(max, dist[i]);
        }

        return max;
    }

    class Edge{
        int node;
        int time;

        public Edge(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    class State{
        int node;
        int dist;

        public State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
