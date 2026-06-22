package graph.bfs;

import java.util.*;

public class BusRoutes {

    //https://leetcode.com/problems/bus-routes/

    public int numBusesToDestination(int[][] routes, int source, int target)
    {
        //edge case
        if(source == target){
            return 0;
        }

        //graph modelling
        Map<Integer, List<Integer>> stopToRoutesMap = new HashMap<>();

        for(int i=0; i<routes.length; i++){
            for(int stop : routes[i]){
                stopToRoutesMap.computeIfAbsent(stop, key -> new ArrayList<>()).add(i);
            }
        }

        //bfs from source stop
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new State(source, 0)); //starting bus stop

        Set<Integer> visitedStops = new HashSet<>();
        visitedStops.add(source); //starting bus stop
        Set<Integer> visitedRoutes = new HashSet<>();

        while(!bfsQueue.isEmpty()){

            State currStop = bfsQueue.poll();

            //exit condition
            if(currStop.stop == target){
                return currStop.dist; //target reached
            }

            //explore neighbouring routes from the current stop
            for(int route : stopToRoutesMap.getOrDefault(currStop.stop, Collections.emptyList())){
                if(!visitedRoutes.contains(route)){
                    visitedRoutes.add(route);//mark this route visited and add all its stops in the bfs queue in the next step

                    //explore all stops in the current route
                    for(int stop : routes[route]){
                        if(!visitedStops.contains(stop)){
                            bfsQueue.add(new State(stop, currStop.dist + 1));
                            visitedStops.add(stop);
                        }
                    }
                }
            }
        }

        return -1;
    }

    static class State{
        int stop;
        int dist;

        public State(int stop, int dist) {
            this.stop = stop;
            this.dist = dist;
        }
    }
}
