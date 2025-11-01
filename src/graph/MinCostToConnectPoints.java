package graph;

import java.util.*;

public class MinCostToConnectPoints {
    //https://neetcode.io/problems/min-cost-to-connect-points?list=neetcode150

    public static void main(String[] args)
    {
        int[][] input = { {0,0},{2,2},{3,3},{2,4},{4,2} };
        System.out.println(minCostConnectPoints(input));
    }

    private static int minCostConnectPoints(int[][] points)
    {
        int n = points.length;

        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int i=0; i<n; i++){
            int xi = points[i][0];
            int yi = points[i][1];
            for(int j=i+1; j<n; j++){
                int xj = points[j][0];
                int yj = points[j][1];

                int dist = Math.abs(xi - xj) + Math.abs(yi - yj);

                graph.get(i).add(new Edge(j, dist));
                graph.get(j).add(new Edge(i, dist)); //since its undirected graph, hence add both ways
            }
        }

        Queue<Edge> pq = new PriorityQueue<Edge>( (a,b) -> a.w - b.w );
        pq.addAll(graph.get(0)); //start with node 0
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        int minCost = 0;
        int numOfEdges = 0;

        while(!pq.isEmpty()){
            Edge minEdge = pq.poll();

            // Skip if already visited
            if (visited.contains(minEdge.v)) {
                continue;
            }

            visited.add(minEdge.v);

            numOfEdges++;
            minCost += minEdge.w;

            if(numOfEdges == n-1){
                return minCost;
            }

            List<Edge> neighbours = graph.get(minEdge.v);
            for(Edge neighbour : neighbours){
                if(!visited.contains(neighbour.v)){
                    pq.add(neighbour);
                }
            }
        }

        return -1;
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
