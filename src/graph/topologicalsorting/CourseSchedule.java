package graph;

import java.util.*;

public class CourseSchedule {

    /*
    Time Complexity = O(V + E)
    V = number of courses (numCourses)
    E = number of prerequisites

    Space Complexity = O(V + E)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //initialize graph and indegree map
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        for(int i=0; i<numCourses; i++){ //Time : O(V)
            graph.put(i, new ArrayList<>());
            indegree.put(i, 0);
        }

        //populate graph and indegree as per prerequisites
        for(int[] p : prerequisites){ //Time : O(E)
            int src = p[1];
            int des = p[0];

            graph.get(src).add(des);
            indegree.put(des, indegree.get(des) + 1);
        }

        Queue<Integer> queue = new ArrayDeque<>(); //for bfs logic
        int processed = 0;

        //collect all starting vertices with 0 indegree in the bfs queue
        for(int key : indegree.keySet()){ //Time : O(V)
            if(indegree.get(key) == 0){
                queue.offer(key);
            }
        }

        //bfs logic
        while(!queue.isEmpty()){ //Time : O(V+E)
            int node = queue.poll();
            processed++;

            for(int neighbour : graph.get(node)){
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if(indegree.get(neighbour) == 0){
                    queue.add(neighbour); //add to bfs queue once indegree becomes 0
                }
            }
        }

        // if all nodes processed → no cycle
        return processed == numCourses;
    }
}
