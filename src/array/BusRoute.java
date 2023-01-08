package array;

import javafx.util.Pair;

import java.util.*;

public class BusRoute {

    public static void main(String[] args)
    {
        int[][] routes = { {0, 1, 2}, {0, 3, 4}, {2, 5, 6}, {4, 7, 6} };

        System.out.println(findMinBus(routes, 5, 7));
    }

    public static int findMinBus(int[][] routes, int source, int destination)
    {
        //todo : refer to BusRoute2 solution next to it
        //collect map of bus stop to bus route number
        Map<Integer, List<Integer>> busStopToBusRouteMap = collectBusStopToBusRouteNumMap(routes);
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedBusRoutes = new HashSet<>();

        queue.add(new Pair<>(source, 0));
        visitedStops.add(source);

        while (!queue.isEmpty()){
            Pair<Integer, Integer> pair = queue.remove();

            if(pair.getKey() == destination){
                return pair.getValue();
            }

            List<Integer> busRoutes = busStopToBusRouteMap.get(pair.getKey());

            for(int route : busRoutes){
                if(!visitedBusRoutes.contains(route)){
                    visitedBusRoutes.add(route);
                    for(int tmpstop : routes[route]){
                        if(!visitedStops.contains(tmpstop)){
                            visitedStops.add(tmpstop);
                            queue.add(new Pair<>(tmpstop, pair.getValue()+1));
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static Map<Integer, List<Integer>> collectBusStopToBusRouteNumMap(int[][] routes)
    {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0; i<routes.length; i++){
            for(int j=0; j<routes[0].length; j++){

                List<Integer> tmplist = map.getOrDefault(routes[i][j], new ArrayList<>());
                tmplist.add(i);
                map.put(routes[i][j], tmplist);
            }
        }
        return map;
    }
}
