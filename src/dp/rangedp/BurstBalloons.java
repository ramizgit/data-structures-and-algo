package dp.rangedp;

public class BurstBalloons {

    public static void main(String[] args)
    {
        maxCoins(new int[]{3,1,5,8});
        maxCoins(new int[]{1,5});
    }

    /*
    ================2-D dp visualize===========
        j →
      0    1    2    3
i ↓
0    dp[0][0] dp[0][1] dp[0][2] dp[0][3]
1             dp[1][1] dp[1][2] dp[1][3]
2                      dp[2][2] dp[2][3]
3                               dp[3][3]

     */

    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int len = 1; len <= n; len++) {

            for (int i = 0; i < n; i++) {

                int j = i + len - 1;
                if (j >= n) break;

                for (int k = i; k <= j; k++) {

                    int leftCoins  = (k == i) ? 0 : dp[i][k - 1];
                    int rightCoins = (k == j) ? 0 : dp[k + 1][j];

                    //boundary condition
                    int leftVal  = (i == 0) ? 1 : nums[i - 1];
                    int rightVal = (j == n - 1) ? 1 : nums[j + 1];

                    int curr = leftCoins + rightCoins + leftVal * nums[k] * rightVal;

                    dp[i][j] = Math.max(dp[i][j], curr);
                }
            }
        }

        return dp[0][n - 1];
    }
}
