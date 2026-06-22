package graph.mst;

import java.util.*;

public class ConnectingCitiesWithMinimumCost {

    //https://leetcode.com/problems/connecting-cities-with-minimum-cost/description/

    public int getMinimumCost(int n, int m, int[][] connections)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph
        for(int[] con : connections){
            int u = con[0];
            int v = con[1];
            int w = con[2];

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); //given two-way roads, hence bidirectional
        }

        //prims algo
        PriorityQueue<State> pq = new PriorityQueue<>( (a, b) -> Integer.compare(a.weight, b.weight) ); //minheap
        pq.offer(new State(1, 0)); //starting point

        boolean[] visited = new boolean[n+1];
        int totalCost = 0;
        int nodeCount = 0;

        while(!pq.isEmpty()){

            State curr = pq.poll();

            if(visited[curr.node]){
                continue;
            }

            //add to MST
            visited[curr.node] = true;
            totalCost += curr.weight;
            nodeCount++;

            //early exit
            if (nodeCount == n) {
                return totalCost;
            }

            //explore neighbours
            List<Edge> neighbours = graph.get(curr.node);
            for(Edge neighbour : neighbours){
                if(!visited[neighbour.v]){
                    pq.offer(new State(neighbour.v, neighbour.w));
                }
            }
        }

        return -1;
    }

    static class Edge{
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
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
