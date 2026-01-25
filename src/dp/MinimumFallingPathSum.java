package dp;

public class MinimumFallingPathSum {
    //https://leetcode.com/problems/minimum-falling-path-sum/description/

    public static void main(String[] args)
    {

    }

    private static int minFallingPathSum(int[][] matrix)
    {
        int n = matrix.length;

        // Copy last row
        int[] dp = matrix[n-1].clone();

        for(int i=n-2; i>=0; i--){
            int[] curr = matrix[i];

            for(int j=0; j<n; j++){
                int left = j == 0 ? Integer.MAX_VALUE : dp[j-1];
                int right = j == n-1 ? Integer.MAX_VALUE : dp[j+1];
                int down = dp[j];

                dp[j] = curr[j] + Math.min(left, Math.min(right, down));

            }
        }

        //collect min
        int min = Integer.MAX_VALUE;
        for(int val : dp){
            min = Math.min(min, val);
        }

        return min;
    }
}
