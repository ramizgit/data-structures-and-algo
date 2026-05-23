package graph.dfs;

import java.util.*;

public class CourseScheduleIIDfs {

    //https://leetcode.com/problems/course-schedule-ii/

    //IMPORTANT : this problem can be solved via both dfs (below impl) and topological sort(CourseScheduleII_topologicalsorting)
    
    /*
    Time complexity :
    Build graph: O(V + E)

    DFS traversal:
    each node visited once
    each edge explored once
    Total: O(V + E)

    Hence total = O(V + E)
     */

    public int[] findOrder(int numCourses, int[][] prerequisites)
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

        //cycle detection
        boolean[] visited = new boolean[numCourses];
        boolean[] inCurrPath = new boolean[numCourses];

        List<Integer> result = new ArrayList<>();

        // run DFS from every unvisited node
        for(int i = 0; i < numCourses; i++){ //Time : O(V)
            if(!visited[i]){
                if(dfs(i, graph, visited, inCurrPath, result)){
                    return new int[0];
                }
            }
        }

        // reverse to get topological order
        Collections.reverse(result);

        // convert list -> int[]
        int[] answer = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            answer[i] = result.get(i);
        }

        return answer;
    }

    // Total DFS complexity = O(V + E)
    private boolean dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] inCurrPath,
                        List<Integer> result)
    {
        //mark visited as well as in curr dfs path true
        visited[node] = true;
        inCurrPath[node] = true;

        //explore all neighbours
        for(int neighbour : graph.get(node)){ //Time : O(E)
            if(!visited[neighbour]){
                if(dfs(neighbour, graph, visited, inCurrPath, result)){
                    return true;
                }
            }else if(inCurrPath[neighbour]){
                return true; //both visited and in curr path, cycle found
            }
        }

        inCurrPath[node] = false; //unmark in curr path for backtrack
        result.add(node); //add to result
        return false;
    }
}
