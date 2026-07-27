package graph.topologicalsorting.topologicalDp;

import java.util.*;

/*
Uolevi has won a contest, and the prize is a free flight trip that can consist of one or more flights through cities. Of course, Uolevi wants to choose a trip that has as many cities as possible.
Uolevi wants to fly from Syrjälä to Lehmälä so that he visits the maximum number of cities. You are given the list of possible flights, and you know that there are no directed cycles in the flight network.
Input
The first input line has two integers n and m: the number of cities and flights. The cities are numbered 1,2,\dots,n. City 1 is Syrjälä, and city n is Lehmälä.
After this, there are m lines describing the flights. Each line has two integers a and b: there is a flight from city a to city b. Each flight is a one-way flight.
Output
First print the maximum number of cities on the route. After this, print the cities in the order they will be visited. You can print any valid solution.
If there are no solutions, print "IMPOSSIBLE".
Constraints

2 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
5 5
1 2
2 5
1 3
3 4
4 5

Output:
4
1 3 4 5
 */

public class LongestFlightRoute {

    //https://cses.fi/problemset/task/1680

    public int findLongestRoute(int n, int[][] routes)
    {
        //build graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n+1]; //initialize indegree as zero for all nodes

        //populate graph and indegree as per prerequisites
        for(int[] route : routes){
            int u = route[0];
            int v = route[1];

            graph.get(u).add(v); //each flight is a one-way flight

            indegree[v]++;
        }

        Queue<Integer> bfsQueue = new ArrayDeque<>();

        for(int i=1; i<=n; i++){
            if(indegree[i] == 0){
                bfsQueue.offer(i);
            }
        }

        int[] dist = new int[n+1]; //dist[u] = maximum number of cities from 1 to u
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[1] = 1; //starting city with count 1

        while(!bfsQueue.isEmpty()){

            int curr = bfsQueue.poll();

            //explore neighbours
            for(int neighbour : graph.get(curr)){

                //state propagation from parent to neighbouring child nodes
                //dist[neighbour] = Math.max(dist[neighbour], dist[curr] + 1);
                if(dist[curr] != Integer.MIN_VALUE){ //why infinity check? : only propagate from nodes that are reachable from the source.
                    dist[neighbour] = Math.max(dist[neighbour], dist[curr] + 1);
                }

                indegree[neighbour]--;
                if(indegree[neighbour] == 0){
                    bfsQueue.offer(neighbour);
                }
            }
        }

        if(dist[n] == Integer.MIN_VALUE){
            return -1; // or throw, depending on API
        }

        return dist[n]; //max number of cities to reach target node n
    }
}
