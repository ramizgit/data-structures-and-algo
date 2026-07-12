package graph.unionfindDSU;

public class RemoveMaxEdgesToKeepGraphFullyTraversable {

    /*
    Approach:

    1. Maintain two DSUs:
       - Alice
       - Bob

    2. Process Type 3 (shared) edges first.
       - Union in both DSUs.
       - If already connected, edge is redundant.
       if (alice.union(u,v)) {
            bob.union(u,v);
        } else {
            removable++;
        }

    3. Process Type 1 edges for Alice only.

    4. Process Type 2 edges for Bob only.

    5. If either DSU is not fully connected, return -1.
       Otherwise, return the number of redundant edges.

    Time : O(E · α(N))
    Space: O(N)
    */

    public int maxNumEdgesToRemove(int n, int[][] edges)
    {
        //todo : implement

        return 0;
    }
}
