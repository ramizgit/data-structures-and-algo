package graph.unionfindDSU;

import java.util.*;

public class NumberOfGoodPaths {

    //https://leetcode.com/problems/number-of-good-paths/description/

    public int numberOfGoodPaths(int[] vals, int[][] edges)
    {
        int n = vals.length;

        //initialize graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            //add undirected edges
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        //group nodes by value
        //process node values in increasing order so DSU only contains valid smaller/equal values
        TreeMap<Integer, List<Integer>> nodesByValue = new TreeMap<>();
        for(int i=0; i<n; i++){
            nodesByValue.computeIfAbsent(vals[i], key -> new ArrayList<>()).add(i);
        }

        //for each group of nodes (with same value) create component using DSU
        UnionFind uf = new UnionFind(n);
        int goodPaths = 0;

        for(int nodeVal : nodesByValue.keySet()){

            //same value nodes
            List<Integer> sameValueNodes = nodesByValue.get(nodeVal);

            // connect with neighbors having value <= current value
            for(int node : sameValueNodes){
                for(int neighbour : graph.get(node)){
                    if(vals[neighbour] <= vals[node]){
                        uf.union(node, neighbour);
                    }
                }
            }

            // important : separate union-building from component-counting because DSU parents stabilize
            // only after all unions for the current value are complete

            // count nodes with current value in each component
            Map<Integer, Integer> componentCount = new HashMap<>();
            for(int node : sameValueNodes) {
                int parent = uf.find(node);
                componentCount.put(parent, componentCount.getOrDefault(parent, 0) + 1);
            }

            // if component has k nodes with same value
            // add k * (k + 1) / 2 good paths
            for(int count : componentCount.values()){
                goodPaths += (count * (count + 1)) / 2;
            }
        }

        return goodPaths;
    }
}
