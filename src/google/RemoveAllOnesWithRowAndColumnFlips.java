package google;

/*
You have a binary matrix grid of size m x n containing only 0s and 1s.
You can perform operations on this matrix where each operation consists of:

Choosing any single row and flipping all values in that row (changing all 0s to 1s and all 1s to 0s), OR
Choosing any single column and flipping all values in that column
Your goal is to determine if it's possible to make all elements in the matrix become 0 (remove all 1s)
by applying any number of these operations.

Return true if you can remove all 1s from the grid, and false if it's impossible.
*/

public class RemoveAllOnesWithRowAndColumnFlips {

    //https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips/description/

    /*
    REASONING BEHIND THE SOLUTION :-
    “At the end, all rows must become identical (all zeros).
    A row flip can only invert an entire row, and column flips affect every row equally.
    Therefore, before any operations, every row must already be either:

    identical to the first row, or
    its exact complement.
    Otherwise it is impossible.”
 */

    public boolean removeOnes(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        //check if each row either same as first row or its complement
        for(int i=1; i<m; i++){
            for(int j=0; j<n; j++){
                //normalize row pattern relative to first element (same/complement rows produce identical patterns)
                int currPattern = grid[i][j] ^ grid[i][0];
                int firstPattern = grid[0][j] ^ grid[0][0];

                if(currPattern != firstPattern){
                    return false;
                }
            }
        }

        return true;
    }
}
