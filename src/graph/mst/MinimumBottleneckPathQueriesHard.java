package graph.mst;

/*
You are given an undirected weighted graph with n nodes numbered 0 to n - 1, represented by an array edges, where:

edges[i] = [u, v, w]

represents an undirected edge between u and v with weight w.

You are also given an array queries, where:

queries[i] = [u, v]

For each query, return the minimum possible value of the maximum edge weight among all paths from u to v.

In other words, among all possible paths from u to v, choose the path whose largest edge weight is as small as possible.

If u == v, the answer is 0.

Constraints
1 <= n <= 10^5
0 <= edges.length <= 2 * 10^5
1 <= w <= 10^9
1 <= queries.length <= 2 * 10^5
0 <= u, v < n
The graph may be disconnected.
Example
Input:
n = 5

edges = [
    [0, 1, 4],
    [0, 2, 2],
    [2, 1, 3],
    [1, 3, 5],
    [2, 3, 6],
    [3, 4, 1]
]

queries = [
    [0, 3],
    [0, 4],
    [1, 2]
]

Output:

[4, 4, 3]

Explanation:

For [0, 3]:

0 → 1 → 3
weights = [4, 5]
maximum = 5

But:

0 → 2 → 1 → 3
weights = [2, 3, 5]
maximum = 5

So actually the answer is 5.

For [0, 4]:

0 → 2 → 1 → 3 → 4
weights = [2, 3, 5, 1]
maximum = 5

So the corrected output is:

[5, 5, 3]
 */

/*
Problem:
For each query (u, v), find the minimum possible value of the
maximum edge weight on any path from u to v.

============================================================
APPROACH 1: SINGLE QUERY / FEW QUERIES
============================================================

For a single query (u, v):

1. Sort edges by weight.
2. Initialize DSU with every node as its own component.
3. Process edges from smallest weight to largest.
4. Union the endpoints of each edge.
5. As soon as u and v become connected, the current edge weight
   is the answer.

Why?
The current weight W is the smallest threshold at which there
exists a path from u to v using only edges <= W.

Time:
DSU initialization       O(V)
Sort edges               O(E log E)
Process E edges          O(E α(V))

===========================================================
APPROACH 2: MANY QUERIES - OPTIMIZED
============================================================

Running the above DSU process separately for every query is too
expensive.

Instead:

1. Sort all edges by weight.
2. Run Kruskal's algorithm once to build a Minimum Spanning Tree
   (MST).
3. Key MST property:

   For any two nodes u and v, the minimum possible value of the
   maximum edge on a path between u and v in the ORIGINAL graph
   equals the maximum edge weight on the unique path between
   u and v in the MST.

4. Now the problem becomes:

   "For each query (u, v), find the maximum edge weight on the
    path between u and v in a TREE."

5. Root the MST and preprocess:
      - depth[node]
      - up[node][j]
          = 2^j-th ancestor of node
      - maxEdge[node][j]
          = maximum edge weight on the path from node to its
            2^j-th ancestor

6. Use Binary Lifting / LCA to answer each query in O(log V).

Complexity:
Kruskal:          O(E log E)
LCA preprocessing: O(V log V)
Each query:        O(log V)

Total:
O(E log E + V log V + Q log V)

Space:
O(V log V + E)
*/

public class MinimumBottleneckPathQueriesHard {

    public int[] minMaxEdgePathQueries(
            int n,
            int[][] edges,
            int[][] queries
    )
    {
        //todo : implement

        return new int[0];
    }
}
