package array;

import javafx.util.Pair;

import java.util.*;

public class BusRoute2 {
    public static void main(String[] args)
    {
        //int[][] routes = { {0, 1, 2}, {0, 3, 4}, {2, 5, 6}, {4, 7, 6} };
        List<Integer> route1 = Arrays.asList(0,1,2);
        List<Integer> route2 = Arrays.asList(0,3,4);
        List<Integer> route3 = Arrays.asList(2,5,6);
        List<Integer> route4 = Arrays.asList(4,7,6);

        List<List<Integer>> routes = Arrays.asList(route1, route2, route3, route4);

        System.out.println(findMinHop(routes, 5, 7));
    }

    public static int findMinHop(List<List<Integer>> routes, int source, int destination)
    {
        Map<Integer, List<Integer>> routeToStopsMap = new HashMap<>();
        Map<Integer, List<Integer>> stopToRoutesMap = new HashMap<>();
        populateStopAndRouteMaps(routes, routeToStopsMap, stopToRoutesMap);

        //BFS
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Set<Integer> visitedRoutes = new HashSet<>();
        Set<Integer> visitedStops = new HashSet<>();

        queue.add(new Pair<>(source, 0));
        visitedStops.add(source);

        while (!queue.isEmpty()){
            Pair<Integer, Integer> stop = queue.poll();

            if(stop.getKey() == destination){
                return stop.getValue();
            }

            List<Integer> routelist = stopToRoutesMap.get(stop.getKey());
            for(int route : routelist){
                if(!visitedRoutes.contains(route)){
                    visitedRoutes.add(route);
                    for(int tmpstop : routeToStopsMap.get(route)){
                        if(!visitedStops.contains(tmpstop)){
                            visitedStops.add(tmpstop);
                            queue.add(new Pair<>(tmpstop, stop.getValue()+1));
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static void populateStopAndRouteMaps(List<List<Integer>> routes, Map<Integer, List<Integer>> routeToStopsMap, Map<Integer, List<Integer>> stopToRoutesMap)
    {
        int routenum=1;

        for(List<Integer> route : routes){
            routeToStopsMap.put(routenum, route);
            for(int stop : route){
                List<Integer> routelist = stopToRoutesMap.getOrDefault(stop, new ArrayList<>());
                routelist.add(routenum);
                stopToRoutesMap.put(stop, routelist);
            }
            routenum++;
        }
    }
}
