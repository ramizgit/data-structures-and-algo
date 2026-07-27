package graph.dijkstra;

public class FloydWarshall {

    //Floyd-Warshall is an all-pairs shortest path algorithm for weighted graphs by considering each vertex as a possible intermediate node.

    /*
    int[][] dist = new int[n][n];

    // 1. Fill with INF
    for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], INF);
    }

    // 2. Distance to self = 0
    for (int i = 0; i < n; i++) {
        dist[i][i] = 0;
    }

    // 3. Add edges
    for (each edge u, v, w) {
        dist[u][v] = Math.min(dist[u][v], w);
        dist[v][u] = Math.min(dist[v][u], w); // only for undirected graphs
    }

    // 4. Run Floyd-Warshall
    floydWarshall(dist);

     */

    //Time : O(n^3)
    public static void floydWarshall(int[][] dist) {
        int n = dist.length;

        // Try every node as intermediate
        for (int k = 0; k < n; k++) {
            // Source
            for (int i = 0; i < n; i++) {
                // Destination
                for (int j = 0; j < n; j++) {

                    // Avoid overflow check
                    if (dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE) {

                        //goal : can I get from i to j more cheaply by going through node k?
                        dist[i][j] = Math.min(
                                dist[i][j],
                                dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }
    }
}

/*
When is Floyd-Warshall feasible?

|    n | n³ Operations |      Feasible?     |
| ---: | ------------: | :----------------: |
|  100 |     1 million |          ✅         |
|  300 |    27 million |          ✅         |
|  500 |   125 million |          ✅         |
| 1000 |     1 billion | ❌ Usually too slow |

 */