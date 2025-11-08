package graph;

import java.util.*;

public class DijkstraAlgorithm {

    public static void main(String[] args)
    {

    }

    //undirected graph
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
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Edge> pq = new PriorityQueue<>( (a,b) -> a.w - b.w );
        pq.add(new Edge(source, 0));
        dist[source] = 0; //starting point

        while(!pq.isEmpty()){
            Edge curr = pq.poll();

            if(curr.w > dist[curr.v]){
                /*
                When you update a distance (relax an edge), you push a new entry into the priority queue:
                …but you don’t remove the old, outdated entry for the same node (Java PQ doesn’t support efficient removal).
                hence this check to ignore such. e.g., below
                (v=2, w=10)
                (v=2, w=5)
                (v=3, w=7)
                 */
                continue;
            }

            List<Edge> neighbours = graph.get(curr.v);
            for(Edge neighbour : neighbours){
                int currDist = dist[neighbour.v];
                int newDist = curr.w + neighbour.w;
                if(newDist < currDist){
                    dist[neighbour.v] = newDist; //relaxation
                    pq.add(new Edge(neighbour.v, newDist));
                }
            }
        }

        return dist;
    }
}

