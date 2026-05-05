package graph.dijkstra;

import java.util.*;

public class DijkstraAlgorithm {

    //undirected weighted graph
    //O((V+E)logE)
    private int[] dijkstra(int n, int[][] edges, int source) {

        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){ //O(V)
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){ //O(E)
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); //undirected graph, hence adding both
        }

        //dijkstra algo
        PriorityQueue<Edge> pq = new PriorityQueue<>( (a,b) -> a.w - b.w ); //minheap
        pq.offer(new Edge(source, 0)); //starting point

        int[] dist = new int[n]; //distance/cost array, our goal to minimize it
        Arrays.fill(dist, Integer.MAX_VALUE); //initial values, to be relaxed later
        dist[source] = 0; //starting point

        while(!pq.isEmpty()){
            Edge curr = pq.poll(); //O(logE)

            //stale entry check
            if(curr.w > dist[curr.v]){
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

            //explore neighbours
            List<Edge> neighbours = graph.get(curr.v);
            for(Edge neighbour : neighbours){
                int newDist = curr.w + neighbour.w;
                int currDist = dist[neighbour.v];
                if(newDist < currDist){
                    dist[neighbour.v] = newDist; //relaxation
                    pq.offer(new Edge(neighbour.v, newDist)); //O(logE)
                }
            }
        }

        return dist;
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

