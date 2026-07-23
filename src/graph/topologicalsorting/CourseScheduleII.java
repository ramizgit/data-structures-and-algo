package graph.topologicalsorting;

import java.util.*;

public class CourseScheduleII {

    //https://leetcode.com/problems/course-schedule-ii/description/

    /*
    Time Complexity : O(V + E)
    Space Complexity : O(V + E)

    V = numCourses
    E = prerequisites.length
    */
    public List<Integer> findOrder(int numCourses, int[][] prerequisites)
    {
        //build graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=0; i<numCourses; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[numCourses]; //initialize indegree as zero for all nodes

        //populate graph and indegree as per input prerequisites
        for(int[] p : prerequisites){
            int u = p[1];
            int v = p[0];

            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> bfsQueue = new ArrayDeque<>(); //for bfs logic
        //Queue<Integer> queue = new PriorityQueue<>(); //NOTE(***) : USE PRIORITY QUEUE IF U NEED ANSWER IN SORTED ORDER WHEREVER POSSIBLE

        //add all vertices with indegree 0.
        //these courses have no prerequisites and can be taken first.
        for(int i=0; i<numCourses; i++){ //Time : O(V)
            if(indegree[i] == 0){
                bfsQueue.offer(i);
            }
        }

        List<Integer> topologicalOrder = new ArrayList<>();

        //bfs logic
        while(!bfsQueue.isEmpty()){

            int node = bfsQueue.poll();
            topologicalOrder.add(node);

            //explore neighbours
            for(int neighbour : graph.get(node)){

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.offer(neighbour); //add to bfs queue once indegree becomes 0
                }
            }
        }

        if(topologicalOrder.size() != numCourses){
            return Collections.emptyList(); // cycle detected - return empty list as we couldn't schedule all courses
        }

        return topologicalOrder;
    }
}
