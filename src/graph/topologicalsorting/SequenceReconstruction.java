package graph.topologicalsorting;

import java.util.*;

public class SequenceReconstruction {

    //https://leetcode.com/problems/sequence-reconstruction/description/

    /*
    Time Complexity : O(V + E)
    Space Complexity : O(V + E)

    V = nums.length
    E = number of unique adjacent pairs
    */
    public boolean sequenceReconstruction(int[] nums, int[][] sequences)
    {
        int n = nums.length;

        //build graph as adjacency list
        Map<Integer, Set<Integer>> graph = new HashMap<>(); //why set? : Prevents duplicate edges from incorrectly increasing indegree.

        for(int i=1; i<=n; i++){
            graph.put(i, new HashSet<>());
        }

        int[] indegree = new int[n+1]; //initialize indegree as zero for all nodes

        // Track which numbers actually appear in the sequences
        // Every number in nums must appear at least once;
        // otherwise the sequence cannot be uniquely reconstructed.
        boolean[] seen = new boolean[n + 1];
        int seenCount = 0;

        // populate graph and indegree
        for (int[] sequence : sequences) {

            for (int num : sequence) {
                if (!seen[num]) {
                    seen[num] = true;
                    seenCount++;
                }
            }

            // build edges between adjacent elements
            //important: a sequence is not necessarily a pair, it may contain multiple chained nodes.
            for (int i = 0; i < sequence.length - 1; i++) {
                int u = sequence[i];
                int v = sequence[i + 1];

                //avoid duplicate edges
                if(!graph.get(u).contains(v)){
                    graph.get(u).add(v);
                    indegree[v]++; //increment indegree only when edge successfully added
                }

                /*if (graph.get(u).add(v)) {
                    indegree[v]++; //increment indegree only when edge successfully added
                }*/
            }
        }

        // Every number in nums must appear at least once.
        if (seenCount != n) {
            return false;
        }

        Queue<Integer> bfsQueue = new ArrayDeque<>(); //for bfs logic
        //Queue<Integer> queue = new PriorityQueue<>(); //NOTE(***) : USE PRIORITY QUEUE IF U NEED ANSWER IN SORTED ORDER WHEREVER POSSIBLE

        //add all vertices with indegree 0.
        //these courses have no prerequisites and can be taken first.
        for(int i=1; i<=n; i++){ //Time : O(V)
            if(indegree[i] == 0){
                bfsQueue.offer(i);
            }
        }

        int index = 0;

        //bfs logic
        while(!bfsQueue.isEmpty()){

            if(bfsQueue.size() > 1){
                return false;
            }

            int node = bfsQueue.poll();

            if(nums[index] != node){
                return false;
            }

            index++;

            //explore neighbours
            for(int neighbour : graph.get(node)){

                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    bfsQueue.offer(neighbour); //add to bfs queue once indegree becomes 0
                }
            }
        }

        return index == n;
    }
}
