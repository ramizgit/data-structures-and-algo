package graph.dijkstra;

public class FloydWarshall {

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
