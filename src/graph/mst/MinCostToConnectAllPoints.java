package graph.mst;

import java.util.*;

public class MinCostToConnectAllPoints {

    //https://leetcode.com/problems/min-cost-to-connect-all-points/description/
    /*
    Return the minimum cost to make all points connected. All points are connected if there is
    exactly one simple path between any two points.
     */

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0; //starting point

        boolean[] visited = new boolean[n];

        int totalCost = 0;

        for (int i = 0; i < n; i++) {

            // pick unvisited node with minimum distance
            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && minDist[j] < min) {
                    u = j;
                    min = minDist[j];
                }
            }

            //add to MST
            visited[u] = true; //mark visited
            totalCost += minDist[u];

            // update distances of remaining nodes from u node
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    minDist[v] = Math.min(minDist[v], dist);
                }
            }
        }
        return totalCost;
    }
}
