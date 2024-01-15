package graph;

import java.util.*;

public class ShortestPathAlgorithms {

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

        System.out.println("Shortest paths via Djkstra algo:"); //O((V+E)logV)
        graph.dijkstra(1);

        Graph graphWithNegativeEdge = new Graph();
        graphWithNegativeEdge.addEdge(1, 2, 4);
        graphWithNegativeEdge.addEdge(1, 4, 5);
        graphWithNegativeEdge.addEdge(4, 3, 3);
        graphWithNegativeEdge.addEdge(3, 2, -10);

        System.out.println("Graph with -ve edge:");
        graphWithNegativeEdge.print();

        System.out.println("Shortest paths via bellman ford algo:"); //0(VE)
        graphWithNegativeEdge.bellmanFord(1);

        Graph unWeightedGraph = new Graph();
        unWeightedGraph.addEdge(1, 2, 1);
        unWeightedGraph.addEdge(1, 3, 1);
        unWeightedGraph.addEdge(2, 4, 1);
        unWeightedGraph.addEdge(3, 5, 1);
        unWeightedGraph.addEdge(4, 6, 1);
        unWeightedGraph.addEdge(5, 6, 1);
        unWeightedGraph.addEdge(2, 6, 1);

        System.out.println("Unweighted graph:");
        unWeightedGraph.print();

        System.out.println("Shortest paths via bfs algo:"); //0(V+E)
        unWeightedGraph.bfs(1);
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

    //O((V+E)logV) time complexity
    public void dijkstra(int source)
    {
        Map<Integer, Integer> shortestDistance = new HashMap<>();
        PriorityQueue<Edge> minheap = new PriorityQueue<>( (e1, e2) -> e1.weight - e2.weight );
        minheap.add(new Edge(source, 0));
        shortestDistance.put(source, 0);

        while (!minheap.isEmpty()){ //O(V)
            Edge minedge = minheap.poll(); //O(logV)

            //get adjacent/neighbour vertices
            List<Edge> neighbours = this.adjList.get(minedge.vertex);
            for(Edge neighbour : neighbours){ //O(E)
                if(!shortestDistance.containsKey(neighbour.vertex) ||
                        (shortestDistance.get(neighbour.vertex) > (minedge.weight + neighbour.weight))) {

                    //put or update shortest distance
                    shortestDistance.put(neighbour.vertex, minedge.weight + neighbour.weight);
                    //System.out.println("logging : vertex : "+neighbour.vertex + " weight : "+(minedge.weight+neighbour.weight));

                    //add neighbours to the minheap
                    minheap.add(new Edge(neighbour.vertex, minedge.weight + neighbour.weight)); //O(logV)
                }
            }
        }

        //Note : total time complexity = O(E+V)logV

        //print
        for(Map.Entry<Integer, Integer> entry : shortestDistance.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

    }

    //0(EV) , works on -ve edges as well
    public void bellmanFord(int source)
    {
        Map<Integer, Integer> shortestDistance = new HashMap<>();
        for(int v : this.adjList.keySet()){
            shortestDistance.put(v, Integer.MAX_VALUE);
        }
        shortestDistance.put(source, 0);

        //iterate V-1 times
        for(int i=0; i<this.adjList.size(); i++){
            //iterate over all edges
            for(int vertex : this.adjList.keySet()){
                for(Edge edge : this.adjList.get(vertex)){
                    if(!shortestDistance.containsKey(edge.vertex) || shortestDistance.get(edge.vertex) > edge.weight + shortestDistance.get(vertex)){
                        shortestDistance.put(edge.vertex, edge.weight + shortestDistance.get(vertex));
                    }
                }
            }
        }

        //print
        for(Map.Entry<Integer, Integer> entry : shortestDistance.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    //0(E+V)
    public void bfs(int source)
    {
        Map<Integer, Integer> shortestDistance = new HashMap<>();
        shortestDistance.put(source, 0);
        Queue<Edge> queue = new LinkedList<>();
        queue.add(new Edge(source, 0));

        while ((!queue.isEmpty())){ //0(V)
            Edge poll = queue.poll();
            for(Edge edge : this.adjList.getOrDefault(poll.vertex, new ArrayList<>())){ //0(E)
                if(!shortestDistance.containsKey(edge.vertex)){
                    shortestDistance.put(edge.vertex, poll.weight + 1);

                    queue.add(new Edge(edge.vertex, poll.weight + 1));
                }
            }
        }

        //print
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

