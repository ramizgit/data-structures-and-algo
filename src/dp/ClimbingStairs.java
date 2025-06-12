package dp;

public class ClimbingStairs {
    public static void main(String[] args)
    {
        System.out.println(climbStairs(1));
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));

        System.out.println("=========================");

        System.out.println(climbStairs2(1));
        System.out.println(climbStairs2(2));
        System.out.println(climbStairs2(3));
        System.out.println(climbStairs2(4));
        System.out.println(climbStairs2(5));
    }

    private static int climbStairs(int n)
    {
        if(n <= 2){
            return n;
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    private static int climbStairs2(int n)
    {
        if(n <= 2){
            return n;
        }

        int minusOne = 2;
        int minusTwo = 1;
        int result = 0;

        for(int i=3; i<=n; i++){
            result = minusOne + minusTwo;

            minusTwo = minusOne;
            minusOne = result;
        }

        return result;
    }
}
