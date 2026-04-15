package graph.dijkstra;

import java.util.*;

public class PathWithMaximumProbability {

    //https://leetcode.com/problems/path-with-maximum-probability/

    /*
    undirected weighted graph
    given two nodes start and end, find the path with the maximum probability of success to go from start to end
    and return its success probability.

    APPROACH:
    use dijkstra also but little tweak, instead of min cost path, we find max prob path, hence maxheap is being used
     */

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph
        for(int i=0; i<edges.length; i++){
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            double prob = succProb[i];

            graph.get(u).add(new Edge(v, prob));
            graph.get(v).add(new Edge(u, prob)); //since undirected graph, hence adding both the edges
        }

        //dijkstra logic
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>( (a,b) -> Double.compare(b.p, a.p));
        pq.offer(new Edge(start_node, 1.0)); //initial probability

        double[] probs = new double[n]; //array to store optimal cost for max prob
        Arrays.fill(probs, 0.0);
        probs[start_node] = 1.0; //initial probability

        while(!pq.isEmpty()){
            Edge curr = pq.poll();

            //early exit
            if(curr.v == end_node) {
                return curr.p;
            }

            //staleness check
            if(curr.p < probs[curr.v]){
                continue;
            }

            //explore neighbours
            List<Edge> neighbours = graph.get(curr.v);
            for(Edge neighbour : neighbours){
                double currProb = probs[neighbour.v];
                double newProb = curr.p * neighbour.p;

                if(newProb > currProb){
                    probs[neighbour.v] = newProb;
                    pq.offer(new Edge(neighbour.v, newProb));
                }
            }
        }

        return probs[end_node];
    }

    class Edge{
        int v;
        double p;

        public Edge(int v, double p) {
            this.v = v;
            this.p = p;
        }
    }
}


