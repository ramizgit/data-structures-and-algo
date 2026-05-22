package graph.dfs;

import java.util.*;

public class CourseScheduleDfs {

    //https://leetcode.com/problems/course-schedule/description/

    /*
    Time complexity :
    Build graph: O(V + E)

    DFS traversal:
    each node visited once
    each edge explored once
    Total: O(V + E)

    Hence total = O(V + E)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites)
    {
        //initialize graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<numCourses; i++){ //Time : O(V)
            graph.put(i, new ArrayList<>());
        }

        //populate edges as input
        for(int[] edge : prerequisites){ //Time : O(E)
            int u = edge[0];
            int v = edge[1];

            graph.get(v).add(u); //directed edge v -> u
        }

        // cycle detection
        boolean[] visited = new boolean[numCourses];
        boolean[] inCurrPath = new boolean[numCourses];

        // run DFS from every unvisited node
        for(int i = 0; i < numCourses; i++){ //Time : O(V)
            if(!visited[i]){
                if(dfs(i, graph, visited, inCurrPath)){
                    return false;
                }
            }
        }

        return true;
    }

    // Total DFS complexity = O(V + E)
    private boolean dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] inCurrPath)
    {
        //mark visited as well as in curr dfs path true
        visited[node] = true;
        inCurrPath[node] = true;

        //explore all neighbours
        for(int neighbour : graph.get(node)){ //Time : O(E)
            if(!visited[neighbour]){
                if(dfs(neighbour, graph, visited, inCurrPath)){
                    return true;
                }
            }else if(inCurrPath[neighbour]){
                return true; //both visited and in curr path, cycle found
            }
        }

        inCurrPath[node] = false; //unmark in curr path for backtrack
        return false;
    }
}
