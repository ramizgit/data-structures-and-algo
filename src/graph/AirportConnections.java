package graph;

import java.util.*;

public class AirportConnections {
    //https://www.youtube.com/watch?v=qz9tKlF431k
    public static void main(String[] args)
    {

    }

    private static int minNewConnections(List<String> airports, List<List<String>> connections, String start)
    {
        //initialize graph
        Map<String, List<String>> graph = new HashMap<>();
        for(String airport : airports){
            graph.put(airport, new ArrayList<>());
        }

        //populate edges
        for(List<String> connection : connections){
            graph.get(connection.get(0)).add(connection.get(1));
        }

        //find reachable airports from the start point
        Set<String> reachable = new HashSet<>();
        dfs(graph, start, reachable);

        //find unreachable airprots
        List<String> unreachable = new ArrayList<>();
        for(String airport : airports){
            if(!reachable.contains(airport)){
                unreachable.add(airport);
            }
        }

        //edge case
        if(unreachable.isEmpty()){
            return 0; //already all airports connected
        }

        //for each unreachable airport, find what other airports it can reach
        Map<String, Set<String>> reachableMapFromUnreachableAirports = new HashMap<>();
        for(String airport : unreachable){
            Set<String> visited = new HashSet<>();
            dfs(graph, airport, visited);

            // Only store those unreachable from the start
            visited.retainAll(unreachable);
            reachableMapFromUnreachableAirports.put(airport, visited);
        }

        //Sort unreachable airports by size of their reachable set (descending)
        Collections.sort(unreachable, (a, b) -> reachableMapFromUnreachableAirports.get(b).size() - reachableMapFromUnreachableAirports.get(a).size());

        //greedily add connections
        int newConnections = 0;
        for(String airport : unreachable){
            if(!reachable.contains(airport)){
                newConnections++;

                //add new edge to this unreachable airprot
                reachable.add(airport);
                //also add other airports reachable from this airport
                reachable.addAll(reachableMapFromUnreachableAirports.get(airport));
            }
        }
        return newConnections;
    }

    //dfs
    private static void dfs(Map<String, List<String>> graph, String start, Set<String> visited)
    {
        visited.add(start);

        List<String> neighbours = graph.get(start);
        for(String neighbour : neighbours){
            if(!visited.contains(neighbour)){
                dfs(graph, neighbour, visited);
            }
        }
    }
}
