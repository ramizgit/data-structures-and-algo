package consistenthashing.graph.topologicalsorting;

import java.util.*;

public class CourseScheduleIV {

    //https://leetcode.com/problems/course-schedule-iv/description/

    /*
    Use Kahn's topological sort.
    For every course, maintain the set of all its direct and indirect prerequisites.
    For edge u -> v, propagate u and all prerequisites of u into v's prerequisite set.
    Answer each query by checking whether a belongs to the prerequisite set of b.
     */

    public boolean[] checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries)
    {
        //build grap as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<numCourses; i++){
            graph.put(i, new ArrayList<>());
        }

        //indegree for kahn's algo
        int[] indegree = new int[numCourses];

        //populate edges
        for(int[] prerequisite : prerequisites){
            int a = prerequisite[0];
            int b = prerequisite[1];

            graph.get(a).add(b); //edge is a -> b

            indegree[b]++;
        }

        Queue<Integer> bfsQueue = new ArrayDeque<>();

        //add all 0 indegree nodes to start with
        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0){
                bfsQueue.offer(i);
            }
        }

        Map<Integer, Set<Integer>> prerequisiteList = new HashMap<>();

        while(!bfsQueue.isEmpty()){

            int curr = bfsQueue.poll();

            //explore neighbours
            for(int neighbour : graph.get(curr)){

                Set<Integer> neighbourPrereqs = prerequisiteList.computeIfAbsent(neighbour, k -> new HashSet<>());

                // direct prerequisite
                neighbourPrereqs.add(curr);

                // indirect prerequisites
                neighbourPrereqs.addAll(prerequisiteList.getOrDefault(curr, Collections.emptySet()));

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.offer(neighbour);
                }
            }
        }

        int n = queries.length;
        boolean[] answer = new boolean[n];

        for(int i=0; i<n; i++){

            int a = queries[i][0];
            int b = queries[i][1];

            answer[i] = prerequisiteList.getOrDefault(b, Collections.emptySet()).contains(a);
        }

        return answer;
    }
}
