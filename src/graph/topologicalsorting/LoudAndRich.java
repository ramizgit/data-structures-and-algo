package graph.topologicalsorting;

import java.util.*;

public class LoudAndRich {

    //https://leetcode.com/problems/loud-and-rich/

    /*
    Build a DAG from richer -> poorer and perform Kahn's Topological Sort.
    Maintain answer[i] as the index of the quietest person among everyone richer than or equal to i.
    During traversal, propagate the quietest known person from each richer node to its poorer neighbours.
    */

    public int[] loudAndRich(int[][] richer, int[] quiet)
    {
        int n = quiet.length;

        //build graph as adj. list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=0; i<n; i++){
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n]; //initialize indegree of each node to 0

        //populate edges
        for(int[] edge : richer){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);

            indegree[v]++;
        }

        int[] answer = new int[n];

        for(int i=0; i<n; i++){
            answer[i] = i; // quiet[i]; // the problem asks you to return the person's index, not their quietness value
        }

        Queue<Integer> bfsQueue = new ArrayDeque<>();

        for(int i=0; i<n; i++){
            if(indegree[i] == 0){
                bfsQueue.offer(i);
            }
        }

        while(!bfsQueue.isEmpty()){

            int curr = bfsQueue.poll();

            //explore neighbours
            for(int neighbour : graph.get(curr)){

                //propagate the least quiet person from each richer node to its poorer neighbours
                if(quiet[answer[curr]] < quiet[answer[neighbour]]){
                    answer[neighbour] = answer[curr];
                }

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.offer(neighbour);
                }

            }
        }

        return answer;
    }
}
