package graph.unionfindDSU;

public class MostStonesRemovedWithSameRowCol {

    //https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/

    /*
    Approach:
    - Treat each stone as a graph node.
    - Two stones are connected if they share a row or a column.
    - Use Union-Find to determine the number of connected components.
    - In each connected component, all but one stone can be removed.

    Answer = Total Stones - Connected Components

    Time : O(n²)
    Space: O(n)
    */

    public int removeStones(int[][] stones)
    {

        //input validation
        if(stones == null || stones.length == 0){
            return 0;
        }

        int n = stones.length;

        UnionFind uf = new UnionFind(n);

        int components = n; //start with each point as individual component

        for(int i=0; i<n; i++){

            int[] stone1 = stones[i];

            for(int j=i+1; j<n; j++){

                int[] stone2 = stones[j];

                if(stone1[0] == stone2[0] //same row
                        || stone1[1] == stone2[1] //same col
                ){
                    if(uf.union(i, j)){
                        components--; //merge components if same row or same col
                    }
                }

            }
        }

        return n - components; //points to be removed = total - retained. Leave one stone in each connected component.
    }
}
