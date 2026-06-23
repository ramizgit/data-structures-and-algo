package consistenthashing.graph;

import java.util.*;

public class MinNumOfVerticesToReachAllNodes {

    //https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/description/

    /*
    Approach:
    - Compute the indegree of every node.
    - In a DAG, nodes with indegree > 0 can be reached from another node.
    - Nodes with indegree = 0 cannot be reached from any other node, so they must be included.
    - Return all nodes with indegree 0.

    Time : O(V + E)
    Space: O(V)
    */

    public List<Integer> findSmallestSetOfVertices(int n, int[][] edges)
    {
        int[] indegree = new int[n]; //start with 0 indegree for all nodes

        //populate indegree as per input edges
        for(int[] edge : edges){ //O(E)
            int v = edge[1];
            indegree[v]++;
        }

        //collect all 0 indegree nodes and return as answer
        List<Integer> result = new ArrayList<>();

        for(int i=0; i<n; i++){ //O(V)
            if(indegree[i] == 0){
                result.add(i);
            }
        }

        return result;
    }
}
