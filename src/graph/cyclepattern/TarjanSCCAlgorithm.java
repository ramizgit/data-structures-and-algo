package graph.cyclepattern;

import java.util.*;

public class TarjanSCCAlgorithm {

    public List<List<Integer>> findSCCs(int n, int[][] edges)
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

        //Tarjan algo
        int[] disc = new int[n]; //discovery time of each node
        int[] low = new int[n]; //lowest discovery time reachable
        Stack<Integer> stack = new Stack<>();
        boolean[] inStack = new boolean[n];

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);

        List<List<Integer>> result = new ArrayList<>();
        int[] timer = new int[1];

        for(int i=0; i<n; i++){
            //dfs
            if(disc[i] == -1){
                dfs(i, disc, low, stack, inStack, timer, graph, result);
            }
        }

        return result;
    }

    private void dfs(int node, int[] disc, int[] low, Stack<Integer> stack, boolean[] inStack, int[] timer,
                     Map<Integer, List<Integer>> graph, List<List<Integer>> result)
    {
        //set discovery and lowest discovery time
        disc[node] = timer[0];
        low[node] = timer[0];
        timer[0]++; //increment time

        //push in stack
        stack.push(node);
        inStack[node] = true;

        //explore neighbours
        for(int neighbour : graph.get(node)){

            if(disc[neighbour] == -1){ //unvisited node
                //tree edge [Edge used to first discover a new node]
                dfs(neighbour, disc, low, stack, inStack, timer, graph, result);
                low[node] = Math.min(low[node], low[neighbour]); //propagate lowest reachable discovery time from child to parent
            }else if(inStack[neighbour]){
                //back edge found [Edge pointing to an ancestor in DFS tree]
                low[node] = Math.min(low[node], disc[neighbour]);
            }

            /*
            We only consider back edges within current SCC, and ignore Cross Edge [Edge between two different DFS branches]
            Ignore edges to already-processed SCCs
             */
        }

        //SCC root condition
        if(disc[node] == low[node]){
            //This node cannot reach any earlier node in DFS → so it must be the head (root) of an SCC.
            //When disc==low, it means this node is the start of an SCC, so we pop all nodes from the stack until we reach it—those form one strongly connected component.

            /*
            During DFS, nodes of an SCC stay connected via back edges
            So their low[] values all collapse to the same smallest discovery time
            👉 The first node of that cycle visited becomes the root
             */

            List<Integer> scc = new ArrayList<>();

            while(true){
                int u = stack.pop();
                inStack[u] = false;
                scc.add(u);
                if(u == node){
                    break;
                }
            }

            result.add(scc);
        }
    }
}
