package graph.cyclepattern;

import java.util.*;

/*
    Kosaraju’s Algorithm finds all Strongly Connected Components (SCCs) in a "directed graph"
    using two DFS passes—one to order nodes by finishing time and another on the reversed graph to extract each component.

    A Strongly Connected Component (SCC) is a maximal group of nodes in a directed graph where every node is reachable
    from every other node; such components contain cycles if their size is greater than one (or if a node has a self-loop).
 */

public class KosarajuAlgorithm {

    public List<List<Integer>> getAllStronglyConnectedComponents(int n, int[][] edges)
    {
        //initialize graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        //populate edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v); //u -> v directed edge
        }

        //kosaraju algorithm

        // Step 1 : First pass DFS in finish order, populating stack with nodes in decreasing order of finishing time
        //Stack<Integer> stack = new Stack<>(); //Stack is legacy + synchronized (slow, outdated), use deque instead
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfsFirstPass(i, visited, graph, stack);
            }
        }

        //Step 2 : Reverse graph
        //initialize reverse graph
        Map<Integer, List<Integer>> revGraph = new HashMap<>();
        for(int i=0; i<n; i++){
            revGraph.put(i, new ArrayList<>());
        }

        //populate reverse edges
        /*for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            revGraph.get(v).add(u); //v -> u reverse directed edge
        }*/

        //another way to populate reverse edges from existing graph
        for(int u : graph.keySet()){
            for(int v : graph.get(u)){
                revGraph.get(v).add(u);
            }
        }

        //Step 3 : Second pass DFS in stack order
        Arrays.fill(visited, false); //reset visited array
        List<List<Integer>> allSCCs = new ArrayList<>();

        while(!stack.isEmpty()){
            int node = stack.pop();

            if(!visited[node]){
                List<Integer> scc = new ArrayList<>();
                dfsSecondPass(node, visited, revGraph, scc);
                allSCCs.add(scc);
            }
        }

        return allSCCs;
    }

    private void dfsFirstPass(int node, boolean[] visited, Map<Integer, List<Integer>> graph, Deque<Integer> stack)
    {
        visited[node] = true; //mark visited

        //explore neighbours
        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                dfsFirstPass(neighbour, visited, graph, stack);
            }
        }

        stack.push(node); //push to stack after exploring all neighbours
    }

    private void dfsSecondPass(int node, boolean[] visited, Map<Integer, List<Integer>> graph, List<Integer> scc)
    {
        visited[node] = true; //mark visited
        scc.add(node); //add to scc list

        //explore neighbours
        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                dfsSecondPass(neighbour, visited, graph, scc);
            }
        }
    }
}
