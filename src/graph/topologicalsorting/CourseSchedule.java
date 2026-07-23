package graph.topologicalsorting;

import java.util.*;

public class CourseSchedule {

    //https://leetcode.com/problems/course-schedule/description/

    //IMPORTANT : MENTION BOTH DFS AND TOPOLOGICAL SORT APPRAOCH. REFER CourseScheduleDfs FOR DFS APPRAOCH
    
    /*
    Appraoch : its a cycle detection problem in graph. this can be solved vai both DFS and topological sorting approach.
    this impl is via topological sorting
    for DFS, follow "CourseScheduleDfs"
    */

    /*
    Time Complexity = O(V + E)
    V = number of courses (numCourses)
    E = number of prerequisites

    Space Complexity = O(V + E)
    */

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //build graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=0; i<numCourses; i++){ //Time : O(V)
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[numCourses]; //initialize indegree as zero for all nodes

        //populate graph and indegree as per prerequisites
        for(int[] p : prerequisites){ //Time : O(E)
            int u = p[1];
            int v = p[0];

            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> bfsQueue = new ArrayDeque<>(); //for bfs logic

        //add all vertices with indegree 0.
        //these courses have no prerequisites and can be taken first.
        for(int i=0; i<numCourses; i++){ //Time : O(V)
            if(indegree[i] == 0){
                bfsQueue.offer(i);
            }
        }

        int processed = 0; //counts courses that can be completed

        while(!bfsQueue.isEmpty()){ //Time : O(V+E)

            int node = bfsQueue.poll();
            processed++;

            //explore neighbours
            for(int neighbour : graph.get(node)){

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.add(neighbour); //add to bfs queue once indegree becomes 0
                }
            }
        }

        //if all nodes processed → no cycle
        return processed == numCourses;
    }
}
