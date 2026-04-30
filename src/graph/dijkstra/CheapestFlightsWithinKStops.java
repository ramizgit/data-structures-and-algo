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
        //initialize graph
        Map<Integer, List<Edges>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph as per input times
        for(int[] flight : flights){
            int u = flight[0];
            int v = flight[1];
            int p = flight[2];

            graph.get(u).add(new Edges(v, p));
        }

        //dijkstra algo
        PriorityQueue<State> pq = new PriorityQueue<>( (a,b) -> a.price - b.price );
        pq.offer(new State(src, 0, 0)); //starting point

        // Modified Dijkstra: state = (node, stops), using 2D dist to handle stop constraint, since cost depends on stops used
        int[][] dist = new int[n][k + 2];
        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[src][0] = 0; //starting point

        while(!pq.isEmpty()){
            State curr = pq.poll();

            //check stale/outdated records
            if (curr.price > dist[curr.dst][curr.stops]) {
                continue;
            }

            //early exit
            if(curr.dst == dst){
                return curr.price;
            }

            //don't proceed if exceeds allowed stops
            if(curr.stops > k) {
                continue;
            }

            //explore neighbours
            for(Edges neighbour : graph.get(curr.dst)){
                int nextNode = neighbour.dst;
                int newCost = curr.price + neighbour.price;
                int newStops = curr.stops + 1;

                if(newCost < dist[nextNode][newStops]){
                    dist[nextNode][newStops] = newCost; //relaxation
                    pq.offer(new State(nextNode, newCost, newStops));
                }
            }
        }

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
        int dst;
        int price;
        int stops;

        public State(int dst, int price, int stops) {
            this.dst = dst;
            this.price = price;
            this.stops = stops;
        }
    }
}


