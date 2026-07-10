package graph.dijkstra;

import java.util.Arrays;

public class BellmanFord {

    /*
    | Algorithm                     | Time Complexity                       | Space    | Negative Weights |
    | ----------------------------- | ------------------------------------- | -------- | ---------------- |
    | **Dijkstra (Priority Queue)** | **O((V + E) log V)                    | O(V + E) | ❌ No             |
    | **Bellman-Ford**              | **O(V × E)**                          | O(V)     | ✅ Yes            |
     */

    /*
    Bellman-Ford Algorithm

    - Finds the shortest distance from a source to every vertex.
    - Supports negative edge weights.
    - Detects negative weight cycles reachable from the source.

    Time : O(V * E)
    Space: O(V)
    */

    public int[] bellmanFord(int n, int[][] edges, int source)
    {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0; //initially only the source is reachable

        // Relax all edges V-1 times
        for (int i = 1; i <= n - 1; i++) {

            for (int[] edge : edges) {

                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                // Skip unreachable vertices
                /*
                We initialize dist[source] = 0, so initially only the source is reachable. During the first iteration,
                only edges leaving the source can be relaxed because every other vertex still has distance ∞.
                As distances to new vertices become finite, their outgoing edges become eligible for relaxation in subsequent iterations.
                 */
                if (dist[u] == Integer.MAX_VALUE) {
                    continue;
                }

                //relaxation
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Detect negative weight cycle
        /*
        Normally, after V-1 passes, every shortest path should already be found.
        So Bellman-Ford performs one extra pass. If any edge can still be relaxed, if(dist[u] + w < dist[v]) then a negative cycle is reachable.
         */
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                throw new IllegalStateException("Negative weight cycle detected");
            }
        }

        return dist;
    }
}
