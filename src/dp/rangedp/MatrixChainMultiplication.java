package dp.rangedp;

public class MatrixChainMultiplication {

    public static int mcm(int[] arr)
    {
        if(arr == null || arr.length < 2){
            return 0;
        }

        int n = arr.length;

        // number of matrices = n - 1
        int[][] dp = new int[n][n];

        for(int len = 2; len < n; len++){
            for (int i = 1; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k=i; k<j; k++){

                    int leftCost = dp[i][k];
                    int rightCost = dp[k+1][j];

                     /*
                    left side matrix (Ai .... Ak) has dimension arr[i-1] × arr[k]
                    right side matrix (Ak+1 .....Aj) has dimention arr[k] × arr[j]
                    hence cost = arr[i - 1] * arr[k] * arr[j]
                    */
                    int currCost = leftCost + rightCost + arr[i-1] * arr[k] * arr[j];

                    dp[i][j] = Math.min(dp[i][j], currCost);
                }
            }
        }

        return dp[1][n-1];
    }
}
