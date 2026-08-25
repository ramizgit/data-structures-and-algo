package graph.topologicalsorting.topologicalDp;

import java.util.*;

public class ParallelCoursesiii {

    //https://leetcode.com/problems/parallel-courses-iii/

    /*
    Topological Sort + DP

    1. Build the graph and compute indegrees.
    2. Process courses in topological order.
    3. finish[i] = earliest time at which course i can be completed.
    4. Relax every edge:
          finish[v] = max(finish[v], finish[u] + time[v]).
    5. The maximum finish time is the minimum months required.

    Time: O(V + E)
    Space: O(V + E)
    */

    public int minimumTime(int n, int[][] relations, int[] time)
    {
        //build graph ad adj. list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n+1];

        //populate edges
        for(int[] relation : relations){
            int u = relation[0];
            int v = relation[1];

            graph.get(u).add(v);

            indegree[v]++;
        }

        //topo sort
        Queue<Integer> topoQueue = new ArrayDeque<>();

        //add 0 degree nodes to start with
        for(int i=1; i<=n; i++){
            if(indegree[i] == 0){
                topoQueue.offer(i);
            }
        }

        //initialize finish time to time of each node
        int[] finish = new int[n+1];
        for(int i=0; i<n; i++){
            finish[i+1] = time[i];
        }

        while(!topoQueue.isEmpty()){

            int curr = topoQueue.poll();

            //explore neighbours
            for(int neighbour : graph.get(curr)){

                //relaxation - update neighbour's earliest finish time using current course
                //important : the relaxation must happen for every incoming edge, not only when indegree becomes 0.
                //a course with multiple prerequisites can start only after ALL of its prerequisites are finished. That's why we take the maximum.

                finish[neighbour] = Math.max(
                        finish[neighbour],                  // earliest finish based on previous prerequisites
                        finish[curr] + time[neighbour-1]    // finish time if curr is the latest prerequisite
                );

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    topoQueue.offer(neighbour);
                }
            }
        }

        int answer = 0;

        for(int t : finish){
            answer = Math.max(answer, t);
        }

        return answer;
    }
}

