package graph;

import java.util.*;

public class DijkstraAlgorithm {

    public static void main(String[] args)
    {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 20);
        graph.addEdge(1, 3, 10);
        graph.addEdge(2, 1, 20);
        graph.addEdge(3, 1, 10);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 30);
        graph.addEdge(4, 2, 10);
        graph.addEdge(4, 3, 30);

        System.out.println("Graph:");
        graph.print();

        System.out.println("Shortest paths:");
        graph.dijkstra(1);
    }
}

class Graph{

    private Map<Integer, List<Edge>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    public void addEdge(int source, int target, int weight)
    {
        List<Edge> edges = this.adjList.getOrDefault(source, new ArrayList<>());
        edges.add(new Edge(target, weight));
        this.adjList.put(source, edges);
    }

    public void dijkstra(int source)
    {
        Map<Integer, Integer> shortestDistance = new HashMap<>();
        PriorityQueue<Edge> minheap = new PriorityQueue<>( (e1, e2) -> e1.weight - e2.weight );
        minheap.add(new Edge(source, 0));
        shortestDistance.put(source, 0);

        while (!minheap.isEmpty()){
            Edge minedge = minheap.poll();

            //get adjacent/neighbour vertices
            List<Edge> neighbours = this.adjList.get(minedge.vertex);
            for(Edge neighbour : neighbours){
                if(!shortestDistance.containsKey(neighbour.vertex) ||
                        (shortestDistance.get(neighbour.vertex) > (minedge.weight + neighbour.weight))) {

                    //put or update shortest distance
                    shortestDistance.put(neighbour.vertex, minedge.weight + neighbour.weight);
                    System.out.println("logging : vertex : "+neighbour.vertex + " weight : "+(minedge.weight+neighbour.weight));

                    //add neighbours to the minheap
                    minheap.add(new Edge(neighbour.vertex, minedge.weight + neighbour.weight));
                }
            }
        }

        //print result
        for(Map.Entry<Integer, Integer> entry : shortestDistance.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    public void print()
    {
        for(Map.Entry<Integer, List<Edge>> entry : this.adjList.entrySet()){
            int source = entry.getKey();
            System.out.print(source + " -->");
            List<Edge> edges = entry.getValue();
            for(Edge edge : edges){
                System.out.print("["+edge.vertex+","+edge.weight+"]");
            }
            System.out.println();
        }
    }

    class Edge{
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
