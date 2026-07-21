package graph.topologicalsorting;

import java.util.*;

public class ParallelCourses {

    //https://leetcode.com/problems/parallel-courses/description/

    /*
    find the minimum number of semesters needed to complete all n courses.
     */

    public int minimumSemesters(int numCourses, int[][] prerequisites)
    {
        //build graph ad adj. list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=1; i<=numCourses; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[numCourses+1];

        //populate edges
        for(int[] prerequisite : prerequisites){
            int u = prerequisite[0];
            int v = prerequisite[1];

            graph.get(u).add(v);

            indegree[v]++;
        }

        Queue<State> bfsQueue = new ArrayDeque<>();

        //collect all starting vertices with 0 indegree in the bfs queue
        //add 0 degree nodes to start with
        for(int i=1; i<=numCourses; i++){
            if(indegree[i] == 0){
                bfsQueue.offer(new State(i, 1));
            }
        }

        int processed = 0;
        int answer = 0;

        while(!bfsQueue.isEmpty()){ //Time : O(V+E)

            State curr = bfsQueue.poll();

            processed++;
            answer = Math.max(answer, curr.semester);

            for(int neighbour : graph.get(curr.course)){

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.add(new State(neighbour, curr.semester + 1)); //add to bfs queue once indegree becomes 0
                }
            }
        }

        return processed == numCourses ? answer : -1;
    }

    static class State {
        int course;
        int semester;

        public State(int course, int semester) {
            this.course = course;
            this.semester = semester;
        }
    }
}


