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
            int w = time[2];

            graph.get(u).add(new Edge(v, w));
        }

        //dijkstra algo
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>( (a,b) -> a.w - b.w );
        pq.offer(new Edge(k, 0)); //source
        dist[k] = 0; //starting point

        while(!pq.isEmpty()){
            Edge curr = pq.poll();

            if(curr.w > dist[curr.v]){
                //stale entry, ignore
                continue;
            }

            //explore neighbours
            List<Edge> neighbours = graph.get(curr.v);
            for(Edge neighbour : neighbours){
                int currDist = dist[neighbour.v];
                int newDist = curr.w + neighbour.w;
                if(newDist < currDist){
                    dist[neighbour.v] = newDist;
                    pq.offer(new Edge(neighbour.v, newDist));
                }
            }
        }

        //collect min time to reach all nodes
        int minTime = 0;
        for(int i=1; i<=n; i++){
            if(dist[i] == Integer.MAX_VALUE){
                return -1; //cant reach all nodes
            }
            minTime = Math.max(minTime, dist[i]);
        }

        return minTime;
    }
}
