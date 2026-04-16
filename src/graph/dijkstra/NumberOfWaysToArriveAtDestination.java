package graph.dijkstra;

import java.util.*;

public class NumberOfWaysToArriveAtDestination {

    //https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/

    /*
    “While running Dijkstra, I maintain a ways array. If I find a shorter path, I replace ways;
    if I find an equally short path, I add ways.”
     */

    public int countPaths(int n, int[][] roads) {

        int MOD = (int)1e9 + 7;

        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph as input roads
        for(int[] road : roads){
            int u = road[0];
            int v = road[1];
            int t = road[2];

            graph.get(u).add(new Edge(v, t));
            graph.get(v).add(new Edge(u, t)); //since bi-directional roads
        }

        //dijkstra algo
        PriorityQueue<Edge> pq = new PriorityQueue<>( (a, b) -> a.time - b.time );
        pq.offer(new Edge(0, 0)); //stating point

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; //stating point

        int[] ways = new int[n];
        Arrays.fill(ways, 0);
        ways[0] = 1; //starting point

        while(!pq.isEmpty()){
            Edge curr = pq.poll();

            //staleness check
            if(curr.time > dist[curr.v]){
                continue; //state entry
            }

            //explore neighbours and do relaxation
            List<Edge> neighbours = graph.get(curr.v);
            for(Edge neighbour : neighbours){
                int newTime = curr.time + neighbour.time;
                int currTime = dist[neighbour.v];
                if(newTime < currTime){
                    dist[neighbour.v] = newTime;
                    ways[neighbour.v] = ways[curr.v]; // copy ways from parent
                    pq.offer(new Edge(neighbour.v, newTime));
                }else if(newTime == currTime){
                    //found another path with same shortest time, hence add ways from parent
                    ways[neighbour.v] = (ways[neighbour.v] + ways[curr.v]) % MOD;
                }
            }
        }

        return ways[n-1];
    }

    class Edge{
        int v;
        int time;

        public Edge(int v, int time){
            this.v =  v;
            this.time = time;
        }
    }
}
