package graph.unionfindDSU;

import java.util.*;

public class BuildingRoads {

    //https://cses.fi/problemset/task/1666

    /*
    Byteland has n cities, and m roads between them. The goal is to construct new roads so that there is a route between any two cities.
    Your task is to find out the minimum number of roads required, and also determine which roads should be built.
    Input
    The first input line has two integers n and m: the number of cities and roads. The cities are numbered 1,2,\dots,n.
    After that, there are m lines describing the roads. Each line has two integers a and b: there is a road between those cities.
    A road always connects two different cities, and there is at most one road between any two cities.
    Output
    First print an integer k: the number of required roads.
    Then, print k lines that describe the new roads. You can print any valid solution.
    Constraints

    1 \le n \le 10^5
    1 \le m \le 2 \cdot 10^5
    1 \le a,b \le n

    Example
    Input:
    4 2
    1 2
    3 4

    Output:
    1
    2 3
     */

    /*
    Your initial reasoning:
    We need n - 1 roads to connect n cities. We already have m roads, so we need (n - 1) - m more roads.
    This would only be true if every existing road were useful, i.e., if the existing graph were already a forest (no cycles).
    The problem is that some roads may be redundant because they form cycles.
     */

    /*
    Approach (DSU)

    1. Initially, every city is its own connected component.
    2. Union all existing roads. Every successful union merges two components, reducing the total number of connected components.
    3. After processing all roads, each DSU root represents one connected component.
    4. Collect all component roots and connect consecutive roots with new roads.
    5. If there are k connected components, exactly (k - 1) roads are required to connect the entire graph, which is the minimum possible.
    */

    public List<int[]> buildRoads(int n, int m, int[][] roads)
    {
        int components = n; // initially each city is its own connected component
        UnionFind uf = new UnionFind(n);

        //union existing roads
        for(int[] road : roads){
            int u = road[0];
            int v = road[1];

            if(uf.union(u, v)){
                components--;
            }
        }

        int minRoadsRequired = components - 1;

        List<Integer> componentRoot = new ArrayList<>();

        for(int i=1; i<=n; i++){
            if(i == uf.find(i)){ //every root represents one connected component
                componentRoot.add(i);
            }
        }

        List<int[]> result = new ArrayList<>();

        for(int i=0; i<componentRoot.size()-1; i++){
            int u = componentRoot.get(i);
            int v = componentRoot.get(i+1);

            //build road between u and v
            result.add(new int[]{u, v});
        }

        return result;
    }
}
