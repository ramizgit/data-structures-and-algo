package consistenthashing.dp.fibonacci;

public class ClimbingStairs {

    //https://leetcode.com/problems/climbing-stairs/description/

    //Time : O(n)
    //Space : O(n)
    private static int climbStairs(int n)
    {
        if(n <= 2){
            return n;
        }

        int[] dp = new int[n+1]; //dp[i] = number of distinct ways to reach step i

        //base cases
        dp[1] = 1;
        dp[2] = 2;

        //populate rest of dp
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] //prev step
                    + dp[i-2]; //two steps behind
        }

        return dp[n];
    }

    private static int climbStairs2(int n)
    {
        if(n <= 2){
            return n;
        }

        int prev2 = 1;
        int prev1 = 2;
        int result = 0;

        for(int i=3; i<=n; i++){
            result = prev1 + prev2;

            prev2 = prev1;
            prev1 = result;
        }

        return result;
    }
}
