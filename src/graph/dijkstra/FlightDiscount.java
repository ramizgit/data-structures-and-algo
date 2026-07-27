package graph.dijkstra;

import java.util.*;

/*
Your task is to find a minimum-price flight route from Syrjälä to Metsälä. You have one discount coupon, using which you can halve the price of any single flight during the route. However, you can only use the coupon once.
When you use the discount coupon for a flight whose price is x, its price becomes \lfloor x/2 \rfloor (it is rounded down to an integer).
Input
The first input line has two integers n and m: the number of cities and flight connections. The cities are numbered 1,2,\ldots,n. City 1 is Syrjälä, and city n is Metsälä.
After this there are m lines describing the flights. Each line has three integers a, b, and c: a flight begins at city a, ends at city b, and its price is c. Each flight is unidirectional.
You can assume that it is always possible to get from Syrjälä to Metsälä.
Output
Print one integer: the price of the cheapest route from Syrjälä to Metsälä.
Constraints

2 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n
1 \le c \le 10^9

Example
Input:
3 4
1 2 3
2 3 1
1 3 7
2 1 5

Output:
2
 */

public class FlightDiscount {

    //https://cses.fi/problemset/task/1195

    //important : this is similar to MinimumCostToReachCityWithDiscounts, except
    //1. we can use discount only ONE time here
    //2. graph is uni-directional

    public int findCheapestRoute(int n, int[][] routes)
    {
        //build graph as adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] route : routes){
            int u = route[0];
            int v = route[1];
            int w = route[2];

            graph.get(u).add(new Edge(v, w)); //edges are unidirectional
        }

        PriorityQueue<State> minHeap = new PriorityQueue<>( (a, b) -> Integer.compare(a.price, b.price) ); //always process flight with cheapest price first
        minHeap.offer(new State(1, 1, 0)); //starting city with one discount left and 0 price

        // price[node][discountLeft]
        // discountLeft = 1 -> coupon still available
        // discountLeft = 0 -> coupon already used
        int[][] price = new int[n+1][2];
        for(int i=1; i<=n; i++){
            Arrays.fill(price[i], Integer.MAX_VALUE); //start with high cost, to be relaxed later
        }
        price[1][1] = 0; //starting city with one discount left and 0 price

        while(!minHeap.isEmpty()){

            State curr = minHeap.poll();

            //stateless check
            if(curr.price > price[curr.node][curr.discountLeft]){
                continue;
            }

            //exit condition
            if(curr.node == n){
                return curr.price; //target city reached
            }

            //explore neighbours
            for(Edge neighbour : graph.get(curr.node)){

                //option 1 : don't use discount
                int newPrice = curr.price + neighbour.weight;
                if(newPrice < price[neighbour.node][curr.discountLeft]){
                    //relaxation
                    price[neighbour.node][curr.discountLeft] = newPrice;
                    minHeap.offer(new State(neighbour.node, curr.discountLeft, newPrice));
                }

                //option 2 : use discount if still left
                if(curr.discountLeft > 0){
                    int newPriceWithDiscount = curr.price + (neighbour.weight / 2);
                    if(newPriceWithDiscount < price[neighbour.node][curr.discountLeft-1]){
                        //relaxation
                        price[neighbour.node][curr.discountLeft-1] = newPriceWithDiscount;
                        minHeap.offer(new State(neighbour.node, curr.discountLeft-1, newPriceWithDiscount));
                    }
                }
            }
        }

        return -1; //should not reach here
    }

    static class State{
        int node;
        int discountLeft; // 1 = coupon available, 0 = coupon already used
        int price;

        public State(int node, int discountLeft, int price) {
            this.node = node;
            this.discountLeft = discountLeft;
            this.price = price;
        }
    }

    static class Edge{
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

}
