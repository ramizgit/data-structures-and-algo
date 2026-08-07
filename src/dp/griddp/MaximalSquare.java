package dp.griddp;

public class MaximalSquare {

    //https://leetcode.com/problems/maximal-square/description/

    /*
    Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     */

    // Time : O(m * n)
    // Space: O(m * n)
    public int maximalSquare(int[][] matrix)
    {
        //input validation
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int maxSquare = 0;

        //dp matrix
        int[][] dp = new int[m][n]; //dp[i][j] = side length of the largest square ending at cell (i, j)

        //populate rest of dp matrix
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == 1){
                    if(i == 0 || j == 0){
                        dp[i][j] = 1; //base case
                    }else{
                        dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                    }

                    maxSquare = Math.max(maxSquare, dp[i][j]);
                }
            }
        }

        return maxSquare * maxSquare; //return area
    }
}
