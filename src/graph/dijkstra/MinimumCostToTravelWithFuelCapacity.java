package consistenthashing.graph.dijkstra;

import java.util.*;

/*
Minimum Cost to Travel

You are given n cities numbered from 0 to n - 1.

There are bidirectional roads between cities. The roads array is given as:

roads[i] = [u, v, fuelRequired]

meaning there is a road between cities u and v, and traveling through this road consumes exactly fuelRequired units of fuel.

You are also given an array: fuelPrice[i] where fuelPrice[i] is the cost of one unit of fuel in city i.

You have a car with a fuel tank capacity of capacity units. The fuel tank is initially empty.

You start at city source and want to reach city destination.

At any city, you may purchase any amount of fuel as long as the fuel in your tank does not exceed capacity.

Traveling along a road consumes fuel but does not cost any additional money.

Return the minimum total amount of money required to reach destination from source.

If it is impossible to reach destination, return -1.
 */

public class MinimumCostToTravelWithFuelCapacity {

    public int minimumCostToTravel(int n, int[][] roads, int[] fuelPrice, int capacity, int source, int destination)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges as per input roads
        for(int[] road : roads){
            int u = road[0];
            int v = road[1];
            int fuel = road[2];

            //add both u->v and v->u since bidirectional roads
            graph.get(u).add(new Edge(v, fuel));
            graph.get(v).add(new Edge(u, fuel));
        }

        //dijkstra algo

        //minheap
        PriorityQueue<State> minheap = new PriorityQueue<>( (a,b) -> a.cost - b.cost ); //minheap to get min cost state efficiently
        minheap.offer(new State(source, 0, 0)); // starting city with empty tank and zero cost incurred

        //cost array {city, fuel} -> cost
        // cost[city][fuel] = minimum cost to reach city with fuel units remaining
        int[][] cost = new int[n][capacity+1];
        for(int i=0; i<n; i++){
            Arrays.fill(cost[i], Integer.MAX_VALUE); //start with max possible cost, will be relaxed later
        }
        cost[source][0] = 0; // starting city with empty tank and zero cost incurred

        while(!minheap.isEmpty()){

            State curr = minheap.poll();

            //staleness check
            if(curr.cost > cost[curr.city][curr.fuel]){
                continue; //stale entry
            }

            //early exit
            if(curr.city == destination){
                return curr.cost; //min cost to reach destination
            }

            /*
             * one possible next state is: "buy one more unit of fuel and stay in the same city"
             * transition 1: buy one unit of fuel but stay in the same city
             * hence fuel increases by 1 and cost increases by fuelPrice[city]
             * Note : buy fuel is itself a graph edge in the augmented state graph.
             */

            if(curr.fuel < capacity){
                int newFuel = curr.fuel + 1;
                int newCost = curr.cost + fuelPrice[curr.city];

                if(newCost < cost[curr.city][newFuel]){
                    cost[curr.city][newFuel] = newCost;
                    minheap.offer(new State(curr.city, newFuel, newCost));
                }
            }

            /*
             * another possible next state is: "travel to neighbouring cities"
             * transition 2: travel to neighbouring cities
             * hence city changes, fuel decreases by neighbour.fuelRequired but cost remains same
             */

            for(Edge neighbour : graph.get(curr.city)){

                if(curr.fuel < neighbour.fuelRequired){
                    continue; //cannot travel
                }

                int newFuel = curr.fuel - neighbour.fuelRequired; //when we reach neighbour, we will (curr.fuel - fuelRequired) fuel left
                int newCost = curr.cost; // travelling itself is free

                if(newCost < cost[neighbour.city][newFuel]){
                    cost[neighbour.city][newFuel] = newCost;
                    minheap.offer(new State(neighbour.city, newFuel, newCost));
                }
            }
        }

        return -1; //destination is unreachable
    }

    class Edge{
        int city;
        int fuelRequired;

        public Edge(int city, int fuelRequired) {
            this.city = city;
            this.fuelRequired = fuelRequired;
        }
    }

    class State{
        int city;
        int fuel; //fuel currently in tank
        int cost;

        public State(int city, int fuel, int cost) {
            this.city = city;
            this.fuel = fuel;
            this.cost = cost;
        }
    }
}
