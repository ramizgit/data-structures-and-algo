package consistenthashing.graph.unionfindDSU;

import java.util.*;

public class UndirectedGraphPathQueries {

    //***GOOGLE
    //https://codezym.com/question/147
    /*
    You are given a list of integers arr of size N, and an integer diff.

Consider an undirected graph where each node corresponds to one index of arr.

Add an edge between nodes i and j if |arr[i] - arr[j]| ≤ diff.

You are also given a list of queries queries, where each query is a comma-separated string "u,v". For each query, return whether there is a path between node u and node v.

A path may use zero or more intermediate nodes.

Example: arr = [1, 2, 3, 6], diff = 2, queries = ["0,2", "1,3"] returns [true, false].
Method Signature
List<Boolean> pathQueries(List<Integer> arr, int diff, List<String> queries)
Parameters
arr is the input list of integers.
diff is the maximum allowed absolute difference for adding an undirected edge.
queries is a list of comma-separated strings, where each string is in the format "u,v".
Return Value
Return a List<Boolean>.
The answer at position k corresponds to the query at position k in queries.
Return true if there is a path between the two queried indices, otherwise return false.
Constraints
1 ≤ N ≤ 10^5
0 ≤ diff ≤ 10^9
-10^9 ≤ arr[i] ≤ 10^9
1 ≤ queries.size() ≤ 10^5
Each query string must contain exactly two valid comma-separated integers u and v.
For every query "u,v", 0 ≤ u < N and 0 ≤ v < N.
arr may contain duplicate values.
You must never use null as a parameter value.
Notes
The graph is undirected.
A node is always reachable from itself.
The path for a query may use intermediate indices.
arr may be sorted in non-decreasing order, sorted in non-increasing order, or unsorted.
If you sort internally to solve the problem efficiently, queries must still refer to the original indices of arr.
     */

    //important : Use sorting to avoid generating O(N²) edges, and then Union Find efficiently maintains the resulting connected components.

    //Time complexity : O(N log N + Q α(N))
    boolean[] pathQueries(int[] arr, int diff, String[] queries)
    {
        List<int[]> valueIdxList = new ArrayList<>(); //list of pair {value, index} from original array
        int n = arr.length;

        for(int i=0; i<n; i++){ //O(n)
            valueIdxList.add(new int[]{arr[i], i});
        }

        //sort input array so that we can create edge of adj. pairs only
        Collections.sort(valueIdxList, (a, b) -> Integer.compare(a[0], b[0])); //O(nlog n)

        graph.unionfindDSU.UnionFind uf = new graph.unionfindDSU.UnionFind(n);

        //iterate and connect adjacent edges if condition meets
        for(int i=1; i<n; i++){ //O(n)
            int[] curr = valueIdxList.get(i);
            int[] prev = valueIdxList.get(i-1);

            if(curr[0] - prev[0] <= diff){
                uf.union(curr[1], prev[1]); //O(a(1))
            }
        }

        //collect answer
        boolean[] answer = new boolean[queries.length];

        for(int i=0; i<queries.length; i++){ //O(Q)

            String[] entry = queries[i].split(",");
            int u = Integer.parseInt(entry[0]);
            int v = Integer.parseInt(entry[1]);

            answer[i] = uf.find(u) == uf.find(v); //O(a(1))
        }

        return answer;
    }
}
