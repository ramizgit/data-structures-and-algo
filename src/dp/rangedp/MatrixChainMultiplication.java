package dp.rangedp;

public class MatrixChainMultiplication {

    //Time Complexity : O(n^3)
    //Space Complexity: O(n^2)
    public static int mcm(int[] arr)
    {
        //input validation
        if(arr == null || arr.length < 2){
            return 0;
        }

        int n = arr.length; //number of matrices = n - 1
        int[][] dp = new int[n][n]; //dp[i][j] : minimum cost to multiply matrices from Ai to Aj

        for(int len = 2; len < n; len++){ //chain length starts from 2 matrices, hence len starts from 2

            for (int i = 1; i < n; i++) { //matrix indexing starts from 1

                int j = i + len - 1;

                if (j >= n) {
                    break; //j goes out of the bound, cant proceed
                }

                dp[i][j] = Integer.MAX_VALUE;

                for(int k=i; k<j; k++){
                    //k is the split point, every split must produce TWO valid NON-EMPTY subproblems, hence k < j, and not k<=j

                    int leftCost = dp[i][k];
                    int rightCost = dp[k+1][j];

                    /*
                    after multiplying matrices from (Ai...Ak), resultant matrix dimension becomes: arr[i-1] × arr[k]
                    after multiplying matrices from (A(k+1)...Aj), resultant matrix dimension becomes: arr[k] × arr[j]
                    therefore multiplication cost: arr[i-1] * arr[k] * arr[j]
                    */
                    int currCost = leftCost + rightCost + arr[i-1] * arr[k] * arr[j];

                    dp[i][j] = Math.min(dp[i][j], currCost);
                }
            }
        }

        return dp[1][n-1];
    }
}
