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
        PriorityQueue<Edge> pq = new PriorityQueue<>( (a, b) -> Integer.compare(a.w, b.w) ); //minheap
        pq.offer(new Edge(1, 0)); //starting point

        boolean[] visited = new boolean[n+1];
        int totalCost = 0;
        int nodeCount = 0;

        while(!pq.isEmpty()){
            Edge curr = pq.poll();

            if(visited[curr.v]){
                continue;
            }

            //add to MST
            visited[curr.v] = true;
            totalCost += curr.w;
            nodeCount++;

            //explore neighbours
            List<Edge> neighbours = graph.get(curr.v);
            for(Edge neighbour : neighbours){
                if(!visited[neighbour.v]){
                    pq.offer(new Edge(neighbour.v, neighbour.w));
                }
            }
        }

        return nodeCount == n ? totalCost : -1;
    }

    class Edge{
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
