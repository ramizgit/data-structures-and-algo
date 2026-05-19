package graph.dijkstra;

import java.util.*;

public class MinimumCostToReachCityWithDiscounts {

    //https://leetcode.com/problems/minimum-cost-to-reach-city-with-discounts/description/

    public int minimumCost(int n, int[][] highways, int discounts)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges as per input
        for(int[] edge : highways){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            //add both u->v and v->u since bidirectional roads
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        //dijkstra algo
        //minheap
        PriorityQueue<State> pq = new PriorityQueue<>( (a,b) -> a.cost - b.cost );
        pq.offer(new State(0, discounts, 0));

        //distance array : dist[node][discountsLeft]
        //important note : in Dijkstra/BFS, the PQ/BFS state and dist/visited state must represent the SAME state space.
        int[][] dist = new int[n][discounts+1];
        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][discounts] = 0; //starting point

        while(!pq.isEmpty()){
            State curr = pq.poll();

            //stale entry check
            if(curr.cost > dist[curr.node][curr.discountLeft]){
                continue;
            }

            //early exit if destination reached
            if(curr.node == n - 1){
                return curr.cost;
            }

            //explore neighbours
            for(Edge neighbour : graph.get(curr.node)){

                //option 1 : don't use discount
                int normalCost = curr.cost + neighbour.w;
                if(normalCost < dist[neighbour.v][curr.discountLeft]){
                    dist[neighbour.v][curr.discountLeft] = normalCost;
                    pq.offer(new State(neighbour.v, curr.discountLeft, normalCost));
                }

                //option 2 : use discount
                if(curr.discountLeft > 0){
                    int discountedCost = curr.cost + (neighbour.w / 2);
                    if(discountedCost < dist[neighbour.v][curr.discountLeft - 1]){
                        dist[neighbour.v][curr.discountLeft - 1] = discountedCost;
                        pq.offer(new State(neighbour.v, curr.discountLeft - 1, discountedCost));
                    }
                }
            }
        }

        return -1;
    }

    class Edge{
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    class State{
        int node;
        int discountLeft;
        int cost;

        public State(int node, int discountLeft, int cost) {
            this.node = node;
            this.discountLeft = discountLeft;
            this.cost = cost;
        }
    }
}
