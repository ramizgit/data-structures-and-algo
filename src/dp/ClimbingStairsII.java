package dp;

public class ClimbingStairsII {
    //https://leetcode.com/problems/climbing-stairs-ii/description/
    public static void main(String[] args)
    {
        System.out.println(mincost2(4, new int[]{1,2,3,4})); //13
        System.out.println(mincost2(4, new int[]{5,1,6,2})); //11
        System.out.println(mincost2(3, new int[]{9,8,3})); //12
    }

    private static int mincost2(int n, int[] cost) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;

            // Try jump from i-1, i-2, i-3
            for (int j = 1; j <= 3; j++) {
                if (i - j >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - j] + cost[i - 1] + (j * j));
                }
            }
        }
        return dp[n];
    }

    /// longer way
    private static int mincost(int n, int[] cost)
    {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = cost[0] + (1-0) * (1-0);
        if (n >= 2) {
            dp[2] = Math.min(
                    dp[0] + cost[1] + (2 - 0) * (2 - 0),
                    dp[1] + cost[1] + (2 - 1) * (2 - 1)
            );
        }
        if (n >= 3) {
            dp[3] = Math.min(
                    dp[0] + cost[2] + (3 - 0) * (3 - 0),
                    Math.min(
                            dp[1] + cost[2] + (3 - 1) * (3 - 1),
                            dp[2] + cost[2] + (3 - 2) * (3 - 2)
                    )
            );
        }

        for(int i=4; i<=n; i++){
            int minusOne = dp[i-1] + cost[i-1] + (i-(i-1)) * (i-(i-1));
            int minusTwo = dp[i-2] + cost[i-1] + (i-(i-2)) * (i-(i-2));
            int minusThree = dp[i-3] + cost[i-1] + (i-(i-3)) * (i-(i-3));

            dp[i] = Math.min(minusOne, Math.min(minusTwo, minusThree));
        }

        return dp[n];
    }
}
