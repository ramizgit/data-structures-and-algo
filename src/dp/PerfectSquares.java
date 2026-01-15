package dp;

import java.util.Arrays;

public class PerfectSquares {
    //https://leetcode.com/problems/perfect-squares/description/

    public static void main(String[] args)
    {
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));
        System.out.println(numSquares(1));
    }

    //// SAME AS COIN CHANGE PROBLEM
    private static int numSquares(int n)
    {
        //populate array of perfect squares
        int[] squares = new int[n+1];
        for(int i=0; i<=n; i++){
            squares[i] = i * i;
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; //base case

        for(int i=1; i<=n; i++){
            for(int s : squares){
                if(s == 0){
                    continue;
                }
                if(i >= s){
                    dp[i] = Math.min(dp[i], 1 + dp[i-s]);
                }
            }
        }

        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }
}
