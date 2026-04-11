package matrix;

public class CountSquareSubmatricesWithAllOnes {

    //https://leetcode.com/problems/count-square-submatrices-with-all-ones/

    public int countSquares(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;

        int maxSquare = 0;
        int totalSquare = 0;

        //dp matrix
        int[][] dp = new int[m][n];

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
                    totalSquare += dp[i][j];
                }
            }
        }

        System.out.println("max square = "+ maxSquare);
        System.out.println("totalSquare square = "+ totalSquare);

        return totalSquare;
    }
}
