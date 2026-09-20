package graph.dijkstra;

import java.util.*;

public class CheapestFlightsWithinKStops {

    //https://leetcode.com/problems/cheapest-flights-within-k-stops/

    /*
    You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
    If there is no such route, return -1.
     */

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k)
    {
        //initialize graph as adj. list
        Map<Integer, List<Edges>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph as per input times
        for(int[] flight : flights){
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];

            graph.get(from).add(new Edges(to, price));
        }

        //dijkstra algo
        PriorityQueue<State> minheap = new PriorityQueue<>( (a,b) -> a.price - b.price ); //always process flight with min price first
        minheap.offer(new State(src, 1, 0)); //starting point

        /*
        Modified Dijkstra:
        state = (node, nodesUsed)
        nodesUsed includes src and current node.
        k stops → at most k + 2 nodes:
        src + k intermediate nodes + dst
        Therefore indices 1..k+2 require k+3 slots.
        */
        int[][] dist = new int[n][k + 3];

        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE); //initially put max possible value, to be relaxed later
        }

        dist[src][1] = 0; //starting point

        while(!minheap.isEmpty()){

            State curr = minheap.poll();

            //check stale/outdated records
            if (curr.price > dist[curr.node][curr.stops]) {
                continue;
            }

            //early exit
            if(curr.node == dst){
                return curr.price;
            }

            //don't proceed if path already uses the maximum allowed nodes
            if(curr.stops >= k+2) {
                continue;
            }

            //explore neighbours
            for(Edges neighbour : graph.get(curr.node)){

                int newCost = curr.price + neighbour.price;
                int newStops = curr.stops + 1;

                //relaxation
                if(newCost < dist[neighbour.dst][newStops]){
                    dist[neighbour.dst][newStops] = newCost; //relaxation
                    minheap.offer(new State(neighbour.dst, newStops, newCost));//enqueue
                }
            }
        }

        /*
        if not early exit, then we need to iterate price for all stops (0 to k+1) for dest node and pick min price

        int ans = Integer.MAX_VALUE;
        for (int nodes = 1; nodes <= k + 2; nodes++) {
           ans = Math.min(ans, dist[dst][nodes]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
         */

        return -1;
    }

    class Edges{
        int dst;
        int price;

        public Edges(int dst, int price) {
            this.dst = dst;
            this.price = price;
        }
    }

    class State{
        int node;
        int stops;
        int price;

        public State(int node, int stops, int price) {
            this.node = node;
            this.stops = stops;
            this.price = price;
        }
    }
}
