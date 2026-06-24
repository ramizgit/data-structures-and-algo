package consistenthashing.graph.dfs;

import java.util.*;

public class IsGraphBipartite {

    //https://leetcode.com/problems/is-graph-bipartite/description/

    /*
    Approach:
    1. Treat the graph as a 2-coloring problem.
    2. Perform DFS from every unvisited node (to handle disconnected components).
    3. Assign one of two colors (0/1) to each node.
    4. During DFS, color every unvisited neighbor with the opposite color.
    5. If an already colored neighbor has the same color as the current node, the graph is not bipartite.
    6. If all nodes can be colored without conflicts, the graph is bipartite.

    Time : O(V + E)
    Space: O(V)
    */

    public boolean isBipartite(int[][] graph)
    {
        //input validation
        if(graph == null){
            return false;
        }

        if(graph.length == 0){
            return true; //empty graph is considered bipartite.
        }

        int n = graph.length;

        //note : The input is already an adjacency list. we dont need to build it again

        int[] nodeColor = new int[n]; //nodeColor repalces visited tracking, as this is sufficient to track unvisited nodes (-1) vs visited nodes (!= -1)
        Arrays.fill(nodeColor, -1); //initially assign -1 color to all nodes

        for(int i=0; i<n; i++){
            if(nodeColor[i] == -1){ //means unvisited node
                if(!dfs(i, 0, graph, nodeColor)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int node, int color, int[][] graph, int[] nodeColor)
    {
        nodeColor[node] = color; //assign color to the node

        //explore neighbours
        for(int neighbour : graph[node]){
            if(nodeColor[neighbour] == -1){ //unvisited neighbour
                int neighbourColor = color == 0 ? 1 : 0; //same as "1-color"
                if(!dfs(neighbour, neighbourColor, graph, nodeColor)){
                    return false;
                }
            }else{
                //already visited neighbour, compare color
                if(nodeColor[neighbour] == color){
                    return false; // Conflict: adjacent nodes have the same color.
                }
            }
        }

        return true;
    }
}
