package graph.mst;

import java.util.*;

//Prim's algo is node driven, while Kruskal is edge driven
public class PrimsMST {

    /*
    similar to dijkstra : uses PQ
    BUT
    1. no relaxation as pq sotres edge cost not path code
    2. prims uses visited array but dijksrta does not (which uses cost array instread)
    3.
     */

    /*
    Time complexity:
    Build graph      O(E)
    PQ operations    O(E log E)
    DFS neighbors    O(E)
    Total : O(E + Elog E) ~ O(ElogE)

    Space : O(V+E)
     */
    public int mstCost(int n, int[][] edges) {

        // initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) { //O(V)
            graph.put(i, new ArrayList<>());
        }

        //populate graph with input edges
        for (int[] e : edges) { //O(E)
            int u = e[0];
            int v = e[1];
            int w = e[2];

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); //undirected graph
        }

        //prims algo
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight); // Always process the node that can be connected to the MST with minimum edge cost.
        pq.offer(new State(0, 0)); //starting point

        boolean[] visited = new boolean[n];
        int totalCost = 0;
        int nodeCount = 0;

        while (!pq.isEmpty()) {
            //find cheapest edge that reaches an unvisited node.
            State curr = pq.poll(); //O(logE) as PQ can contain up to O(E)

            // skip if already part of MST
            if (visited[curr.node]) {
                continue;
            }

            //add to MST
            visited[curr.node] = true; //prims marks visited only when the minimum-cost entry is extracted from the priority queue (dequeued), in contrary to normal bfs which mark visited when discovered (enqueued).
            totalCost += curr.weight;
            nodeCount++; //increase node count, as prims is node-driven algorithm

            //early exit
            if (nodeCount == n) {
                return totalCost;
            }

            // explore neighbors
            for (Edge neighbour : graph.get(curr.node)) { //O(E)
                if (!visited[neighbour.node]) {
                    pq.offer(new State(neighbour.node, neighbour.weight)); //O(log E)
                }
            }
        }

        return -1;
    }

    class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class State{
        int node;
        int weight;

        State(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
