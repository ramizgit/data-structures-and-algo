package graph;

import java.util.*;

public class DetonateTheMaximumBombs {

    //https://leetcode.com/problems/detonate-the-maximum-bombs/description/

    /*Given the list of bombs, return the maximum number of bombs that can be detonated
    if you are allowed to detonate only one bomb.

    bombs = [[2,1,3],[6,1,4]]

     bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb,
     whereas ri denotes the radius of its range.
     */

    public int maximumDetonation(int[][] bombs)
    {
        //initialize graph
        int n = bombs.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate graph as inputs
        //check all pairs of bombs if they are connected
        for(int i=0; i<n; i++){

            //bomb i coordiantes
            int x1 = bombs[i][0];
            int y1 = bombs[i][1];
            int r1 = bombs[i][2];

            for(int j=i+1; j<n; j++){

                //bomb j coordinates
                int x2 = bombs[j][0];
                int y2 = bombs[j][1];
                int r2 = bombs[j][2];

                //distance between the coordinates, via pythogorean theorem
                int dist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);

                if(dist <= r1 * r1){
                    graph.get(i).add(j); //j falls under radius range of i bomb
                }

                if(dist <= r2 * r2){
                    graph.get(j).add(i); //i falls under radius range of j bomb
                }
            }
        }

        //now do dfs from each 0 to n-1 bombs and track max reachable bombs
        int max = 0;
        for(int i=0; i<n; i++){
            int[] count = new int[1];
            Set<Integer> visited = new HashSet<>();
            dfs(i, count, visited, graph);
            max = Math.max(max, count[0]);
        }

        return max;
    }

    private void dfs(int node, int[] count, Set<Integer> visited, Map<Integer, List<Integer>> graph)
    {
        visited.add(node);
        count[0]++;

        //explore neighbours
        for(int neighbour : graph.get(node)){
            if(!visited.contains(neighbour)){
                dfs(neighbour, count, visited, graph);
            }
        }
    }
}
