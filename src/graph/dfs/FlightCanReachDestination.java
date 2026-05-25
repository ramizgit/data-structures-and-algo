package graph.dfs;

import java.util.*;

public class FlightCanReachDestination {

    public boolean canReachDestination(String[][] flights, String source, String destination, int startTime)
    {
        //build graph
        Map<String, List<Flight>> graph = new HashMap<>();
        for(String[] flight : flights){
            String src = flight[0];
            String dest = flight[1];
            int departure = Integer.parseInt(flight[2]);
            int arrival = Integer.parseInt(flight[3]);


            graph.computeIfAbsent(src, key -> new ArrayList<>()).add(new Flight(dest, arrival, departure));
        }

        //run dfs
        return dfs(source, destination, startTime, graph, new HashSet<>());
    }

    private boolean dfs(String source, String destination, int arrivalTime, Map<String, List<Flight>> graph, Set<String> visited)
    {
        //time-aware visited state
        //visited must include arrival time because reaching same airport at different times leads to different future flight possibilities
        visited.add(source + ":"+ arrivalTime);

        //exit condition
        if(source.equals(destination)){
            return true;
        }

        //explore neighbours
        for(Flight neighbour : graph.getOrDefault(source, Collections.emptyList())){
            if(arrivalTime <= neighbour.departure && !visited.contains(neighbour.dest + ":" + neighbour.arrival)){
                if(dfs(neighbour.dest, destination, neighbour.arrival, graph, visited)){
                    return true;
                }
            }
        }

        return false;
    }



    class Flight{
        String dest;
        int arrival;
        int departure;

        public Flight(String dest, int arrival, int departure) {
            this.dest = dest;
            this.arrival = arrival;
            this.departure = departure;
        }
    }
}
