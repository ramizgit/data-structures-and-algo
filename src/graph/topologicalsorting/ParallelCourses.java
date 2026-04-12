package graph;

import java.util.*;

public class ParallelCourses {

    //https://leetcode.com/problems/parallel-courses/description/

    /*
    find the minimum number of semesters needed to complete all n courses.
     */

    public int minimumSemesters(int numCourses, int[][] prerequisites) {
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

        Queue<Course> queue = new ArrayDeque<>(); //for bfs logic

        //collect all starting vertices with 0 indegree in the bfs queue
        for(int key : indegree.keySet()){ //Time : O(V)
            if(indegree.get(key) == 0){
                queue.offer(new Course(key, 1));
            }
        }

        //bfs logic
        int processed = 0;
        int semesters = 0;
        while(!queue.isEmpty()){ //Time : O(V+E)
            Course curr = queue.poll();

            processed++;
            semesters = Math.max(semesters, curr.semester);

            for(int neighbour : graph.get(curr.num)){
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if(indegree.get(neighbour) == 0){
                    queue.add(new Course(neighbour, curr.semester + 1)); //add to bfs queue once indegree becomes 0
                }
            }
        }

        return processed == numCourses ? semesters : -1;
    }
}

class Course{
    int num;
    int semester;

    public Course(int num, int semester) {
        this.num = num;
        this.semester = semester;
    }
}
