package consistenthashing.graph.eulerianpath;

import java.util.*;

//Eulerian Path (Euler Trail)
public class ReconstructItinerary {

    //https://leetcode.com/problems/reconstruct-itinerary/description/

    /*
    Normal DFS:
    visit node once

    Hierholzer:
    consume edges once
    add node in postorder
     */

    /*
    Many people initially try
    DFS + visited[]
    That doesn't work.
    Why?
    Because in graphs we usually mark vertices as visited.
    Here we need to mark edges (tickets) as used.
    In fact, the optimal solution uses Hierholzer's Algorithm, which is specifically designed to construct an Eulerian path.
    The lexicographical requirement is handled by always choosing the smallest available destination first
     (often using a min-heap or sorted adjacency list).
     */

    public List<String> findItinerary(String[][] tickets)
    {
        /*
        Build graph

        For:
        JFK -> SFO
        JFK -> ATL
        ATL -> JFK
        ATL -> SFO
        SFO -> ATL

        Build:
        JFK : [ATL, SFO]
        ATL : [JFK, SFO]
        SFO : [ATL]
         */

        Map<String, PriorityQueue<String>> graph = new HashMap<>(); //minheap as we need airports in lexi order

        for(String[] ticket : tickets){
            String from = ticket[0];
            String to = ticket[1];

            graph.computeIfAbsent(from, key -> new PriorityQueue<>()).offer(to);
        }

        //Hierholzer's Algorithm
        List<String> result = new ArrayList<>();
        dfs("JFK", graph, result);
        Collections.reverse(result);

        return result;
    }

    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, List<String> result)
    {
        /*
        continue DFS while there exists an outgoing edge.
         */
        PriorityQueue<String> destinations = graph.getOrDefault(airport, new PriorityQueue<>());

        //process all unused outgoing tickets
        while(!destinations.isEmpty()){
            //this is the key Hierholzer step - We're marking ticket used by removing it from the graph using poll() method
            dfs(destinations.poll(), graph, result);
        }

        //add to result once there are no unused tickets left
        result.add(airport);
    }
}
