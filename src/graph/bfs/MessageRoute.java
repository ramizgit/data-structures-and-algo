package graph.bfs;

import java.util.*;

/*
Syrjälä's network has n computers and m connections. Your task is to find out if Uolevi can send a message to Maija, and if it is possible, what is the minimum number of computers on such a route.
Input
The first input line has two integers n and m: the number of computers and connections. The computers are numbered 1,2,\dots,n. Uolevi's computer is 1 and Maija's computer is n.
Then, there are m lines describing the connections. Each line has two integers a and b: there is a connection between those computers.
Every connection is between two different computers, and there is at most one connection between any two computers.
Output
If it is possible to send a message, first print k: the minimum number of computers on a valid route. After this, print an example of such a route. You can print any valid solution.
If there are no routes, print "IMPOSSIBLE".
Constraints

2 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
5 5
1 2
1 3
1 4
2 3
5 4

Output:
3
1 4 5
 */

public class MessageRoute {

    //https://cses.fi/problemset/task/1667

    public void sendMessage(int n, int m, int[][] connections)
    {
        //build graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] connection : connections){
            int u = connection[0];
            int v = connection[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        //bfs logic
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(1, 1)); //starting computer node

        boolean[] visited = new boolean[n+1];
        visited[1] = true; //starting computer node

        int minDistance = -1;

        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //exit condition
            if(curr.node == n){
                //target reached
                minDistance = curr.distance;
                break;
            }

            //explore neighbours
            for(int neighbour : graph.get(curr.node)){
                if(!visited[neighbour]){
                    visited[neighbour] = true;
                    bfsQueue.offer(new State(neighbour, curr.distance + 1));

                    parent[neighbour] = curr.node; //track parent
                }
            }
        }

        if(minDistance == -1){
            //not feasible to send message
            return;
        }

        //construct path
        List<Integer> path = new ArrayList<>();

        int curr = n;

        while(curr != -1){
            path.add(curr);
            curr = parent[curr];
        }

        Collections.reverse(path);

        //return path
    }

    static class State{
        int node;
        int distance;

        public State(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

}
