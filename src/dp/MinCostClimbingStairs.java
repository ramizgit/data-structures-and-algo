package dp;

public class MinCostClimbingStairs {

    //https://leetcode.com/problems/min-cost-climbing-stairs/description/

    public static void main(String[] args)
    {
        System.out.println(minCostClimbingStairs(new int[]{10,15,20})); //15
        System.out.println(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1})); //6
    }

    private static int minCostClimbingStairs(int[] cost)
    {
        int n = cost.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;

        for(int i=2; i<=n; i++){
            int minOneCost = dp[i-1] + cost[i-1];
            int minTwoCost = dp[i-2] + cost[i-2];
            dp[i] = Math.min(minOneCost, minTwoCost);
        }

        return dp[n];
    }
}
