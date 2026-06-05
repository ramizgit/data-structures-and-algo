package graph.topologicalsorting;

import java.util.*;

public class CourseScheduleII {

    private static List<Integer> findOrder(int numCourses, int[][] prerequisites)
    {
        //initialize graph and indegree map
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int i=0; i<numCourses; i++){
            graph.put(i, new ArrayList<>());
            indegree.put(i, 0);
        }

        //populate graph and indegree as per input prerequisites
        for(int[] p : prerequisites){
            int src = p[1];
            int des = p[0];

            graph.get(src).add(des);
            indegree.put(des, indegree.get(des) + 1);
        }

        Queue<Integer> queue = new ArrayDeque<>(); //for bfs logic
        //Queue<Integer> queue = new PriorityQueue<>(); //NOTE(***) : USE PRIORITY QUEUE IF U NEED ANSWER IN SORTED ORDER WHEREVER POSSIBLE

        //collect all starting vertices with 0 indegree in the bfs queue
        for(int key : indegree.keySet()){ //Time : O(V)
            if(indegree.get(key) == 0){
                queue.offer(key);
            }
        }

        List<Integer> topologicalOrder = new ArrayList<>();

        //bfs logic
        while(!queue.isEmpty()){
            int node = queue.poll();
            topologicalOrder.add(node);

            //explore neighbours
            for(int neighbour : graph.get(node)){
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if(indegree.get(neighbour) == 0){
                    queue.add(neighbour); //add to bfs queue once indegree becomes 0
                }
            }
        }

        if(topologicalOrder.size() != numCourses){
            return Collections.emptyList(); // cycle detected - return empty list as we couldn't schedule all courses
        }

        return topologicalOrder;
    }
}
