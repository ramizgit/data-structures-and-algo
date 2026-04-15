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

            graph.get(u).add(new Edges(v, p, 0));
        }

        //dijkstra algo
        PriorityQueue<Edges> pq = new PriorityQueue<>( (a,b) -> a.price - b.price );
        pq.offer(new Edges(src, 0, 0)); //source

        int[] stopsArr = new int[n];
        Arrays.fill(stopsArr, Integer.MAX_VALUE);

        while(!pq.isEmpty()){
            Edges curr = pq.poll();

            //breaking condition
            if(curr.dst == dst){
                return curr.price;
            }

            //exceed allowed stops
            if(curr.stops > k) {
                continue;
            }

            //if i already reached this node with fewer stops, ignore this path
            if(curr.stops >= stopsArr[curr.dst]) {
                continue;
            }
            stopsArr[curr.dst] = curr.stops;

            //explore neighbours
            for(Edges neighbour : graph.get(curr.dst)){
                pq.offer(new Edges(neighbour.dst, curr.price + neighbour.price, curr.stops + 1));
            }
        }

        return -1;
    }
}

class Edges{
    int dst;
    int price;
    int stops;

    public Edges(int dst, int price, int stops) {
        this.dst = dst;
        this.price = price;
        this.stops = stops;
    }
}
