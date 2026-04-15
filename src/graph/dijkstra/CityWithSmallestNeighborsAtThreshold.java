package graph.dijkstra;

import java.util.*;

public class CityWithSmallestNeighborsAtThreshold {

    //https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/

    /*
    APPROACH : [multi source dijkstra]
    For each city, run Dijkstra to compute shortest paths,
    count how many cities are within the threshold, and pick the city with minimum count,
    breaking ties by largest index
     */

    public int findTheCity(int n, int[][] edges, int distanceThreshold)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph as input edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); //since bidirection/undirected graph, hence adding both edges
        }

        //run dijkstra algo to find cities reachable for each city
        int minCity = 0;
        int minCost = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            int cost = minDistViaDijkstra(i, n, distanceThreshold, graph);
            if(cost < minCost || (cost == minCost && i > minCity)){
                minCost = cost;
                minCity = i;
            }
        }

        return minCity;
    }

    public int minDistViaDijkstra(int src, int n, int distanceThreshold, Map<Integer, List<Edge>> graph)
    {
        PriorityQueue<Edge> pq = new PriorityQueue<>( (a, b) -> a.w - b.w ); //minheap
        pq.offer(new Edge(src, 0)); //starting point

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0; //starting point

        while(!pq.isEmpty()){
            Edge curr = pq.poll();

            if(curr.w > dist[curr.v]){
                continue; //stale date, ignore and continue
            }

            //explore neighbours
            List<Edge> neighbours = graph.get(curr.v);
            for(Edge neighbour : neighbours){
                int currDist = dist[neighbour.v];
                int newDist = curr.w + neighbour.w;

                if(newDist < currDist){
                    dist[neighbour.v] = newDist; //relaxation
                    pq.offer(new Edge(neighbour.v, newDist));
                }
            }
        }

        //we have shortest path computed from src to all other nodes, now count cities reachable within the threshold
        int count = 0;
        for(int d : dist){
            if(d != 0 && d <= distanceThreshold){
                count++;
            }
        }

        return count;
    }

    class Edge{
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
