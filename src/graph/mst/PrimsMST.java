package graph.mst;

import java.util.*;

//Prim's algo is node driven, while Kruskal is edge driven
public class PrimsMST {

    public int mstCost(int n, int[][] edges) {

        // initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        //populate graph with input edges
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); //undirected graph
        }

        //prims algo
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight); //minheap
        pq.offer(new Edge(0, 0)); //starting point

        boolean[] visited = new boolean[n];
        int totalCost = 0;
        int nodeCount = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            // skip if already part of MST
            if (visited[curr.v]) {
                continue;
            }

            //add to MST
            visited[curr.v] = true;
            totalCost += curr.weight;
            nodeCount++; //increase node count, as prims is node-driven algorithm

            // explore neighbors
            for (Edge neighbour : graph.get(curr.v)) {
                if (!visited[neighbour.v]) {
                    pq.offer(new Edge(neighbour.v, neighbour.weight));
                }
            }
        }

        return nodeCount == n ? totalCost : -1;
    }

    class Edge {
        int v;
        int weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}
