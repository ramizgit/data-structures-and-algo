package dp.rangedp;

public class BurstBalloons {

    //https://leetcode.com/problems/burst-balloons/description/

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

        //input validation
        if(nums == null || nums.length == 0){
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[n][n]; //dp[i][j] : max coins collected by bursting balloons from ith to jth index

        for (int len = 1; len <= n; len++) { //burst balloons starting with length 1 till n

            for (int i = 0; i < n; i++) {

                int j = i + len - 1; //i - > j range tells how many balloons we want to process in current loop

                if (j >= n) {
                    break; //j goes out of the bound, cant proceed
                }

                //try every k in [i...j] as the last balloon to burst
                for (int k = i; k <= j; k++) {

                    //subproblems
                    int leftCoins  = (k == i) ? 0 : dp[i][k - 1]; //left interval
                    int rightCoins = (k == j) ? 0 : dp[k + 1][j]; //right interval

                    //neighbors remaining when k is burst last in the range [i..j]
                    int leftVal  = (i == 0) ? 1 : nums[i - 1];
                    int rightVal = (j == n - 1) ? 1 : nums[j + 1];

                    int curr = leftCoins //burst left side first
                            + rightCoins //burst right side frist
                            + leftVal * nums[k] * rightVal; //coins from bursting k last

                    dp[i][j] = Math.max(dp[i][j], curr);
                }
            }
        }

        return dp[0][n - 1]; //max coins collected by bursting ALL balloons in the range 0 to n-1
    }
}
