package graph.unionfindDSU;

public class NumberOfProvinces {

    //https://leetcode.com/problems/number-of-provinces/description/

    /*
    Approach:
    1. Initially treat every city as its own province.
    2. Traverse the upper half of the adjacency matrix.
    3. If two cities are directly connected, union them.
    4. Every successful union merges two provinces, so decrement the count.

    Time : O(n²)
    Space: O(n)
    */

    public int findCircleNum(int[][] isConnected)
    {
        //input validation
        if(isConnected == null || isConnected.length == 0){
            return 0;
        }

        int n = isConnected.length;

        //initialize uinion find - O(n)
        UnionFind uf = new UnionFind(n);

        int provinces = n; //start with each city as its own provinces

        //iterate matrix and union
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){

                if(isConnected[i][j] == 1){
                    if(uf.union(i, j)){
                        provinces--;
                    }
                }

            }
        }

        return provinces;
    }
}
