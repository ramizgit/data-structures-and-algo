package graph.dijkstra;

import java.util.*;

public class DijkstraAlgorithm {

    //undirected weighted graph
    //O((V+E)logV)
    private static int[] dijkstra(int v, int[][] edges, int source) {

        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<v; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
            graph.get(edge[1]).add(new Edge(edge[0], edge[2])); //undirected graph, hence adding both
        }

        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE); //initial values, to be relaxed later

        PriorityQueue<Edge> pq = new PriorityQueue<>( (a,b) -> a.w - b.w );
        pq.offer(new Edge(source, 0));
        dist[source] = 0; //starting point

        while(!pq.isEmpty()){
            Edge curr = pq.poll();

            if(curr.w > dist[curr.v]){ //stale entry
                /*
                If current weight is more than what we already computed, then ignore, as it is outdated.
                When you update a distance (relax an edge), you push a new entry into the priority queue:
                …but you don’t remove the old, outdated entry for the same node (Java PQ doesn’t support efficient removal).
                hence this check to ignore such. e.g., below
                0 → 1 (10)
                0 → 2 (5)
                2 → 1 (3)
                 */
                continue;
            }

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

        return dist;
    }
}

class Edge{
    int v;
    int w;

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
