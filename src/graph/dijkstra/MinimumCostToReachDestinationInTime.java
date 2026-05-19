package graph.dijkstra;

import java.util.*;

public class MinimumCostToReachDestinationInTime {

    //https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time/description/

    //Pattern : Shortest Path on Augmented State Space

    public int minCost(int maxTime, int[][] edges, int[] passingFees)
    {
        int n = passingFees.length;

        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int t = edge[2];

            //add both u->v and v->u since bi-directional roads
            graph.get(u).add(new Edge(v, t));
            graph.get(v).add(new Edge(u, t));
        }

        //disjkstara algo
        PriorityQueue<State> pq = new PriorityQueue<>( (a,b) -> a.cost - b.cost ); //minheap by cost
        pq.offer(new State(0, 0, passingFees[0])); //starting city

        //distance array : {node, timeSpent}
        int[][] dist = new int[n][maxTime + 1];
        for(int i=0; i<dist.length; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = passingFees[0]; //starting node 0 with 0 time and passingFees[0] cost

        while(!pq.isEmpty()){
            State curr = pq.poll();

            //stale entry check
            if(curr.cost > dist[curr.node][curr.time]){
                continue;
            }

            //early exit
            if(curr.node == n-1){
                return curr.cost;
            }

            //explore neighbours
            for(Edge neighbour : graph.get(curr.node)){
                int newCost = curr.cost + passingFees[neighbour.node];
                int newTime = curr.time + neighbour.time;

                if(newTime > maxTime){ //max time constraint
                    continue;
                }

                if(newCost < dist[neighbour.node][newTime]){
                    //relaxation
                    dist[neighbour.node][newTime] = newCost;

                    //enqueue
                    pq.offer(new State(neighbour.node, newTime, newCost));
                }
            }
        }

        return -1;
    }

    class Edge{
        int node;
        int time;

        public Edge(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    class State{
        int node;
        int time;
        int cost;

        public State(int node, int time, int cost) {
            this.node = node;
            this.time = time;
            this.cost = cost;
        }
    }
}
