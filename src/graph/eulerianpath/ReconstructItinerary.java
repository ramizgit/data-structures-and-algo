package consistenthashing.graph.eulerianpath;

import java.util.*;

public class ReconstructItinerary {

    //https://leetcode.com/problems/reconstruct-itinerary/description/

    /*
    Normal DFS:
    visit node once

    Hierholzer:
    consume edges once
    add node in postorder
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
        continue DFS only while there exists an unused outgoing edge.
         */
        PriorityQueue<String> destinations = graph.get(airport);

        //airport might not exist as a source node in the graph, hence graph.get(airport) can be null
        while(destinations != null && !destinations.isEmpty()){

            /*
            This is the key Hierholzer step - We're marking ticket used by removing it from the graph using poll() method
             */
            String next = destinations.poll(); // consume ticket

            dfs(next, graph, result);
        }

        //add to result once there are no unused tickets left
        result.add(airport);
    }
}
