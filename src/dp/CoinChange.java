package dp;

import java.util.Arrays;

public class CoinChange {
    //https://leetcode.com/problems/coin-change/description/
    public static void main(String[] args)
    {
        System.out.println(minCoinChange(new int[]{1,2,5}, 11)); //3
        System.out.println(minCoinChange(new int[]{1,2}, 3)); //2
        System.out.println(minCoinChange(new int[]{2}, 3)); //-1
        System.out.println(minCoinChange(new int[]{2}, 0)); //0
    }

    private static int minCoinChange(int[] coins, int amount)
    {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;

        for(int i=1; i<=amount; i++){
            for(int c : coins){
                if(i - c >= 0){
                    dp[i] = Math.min(dp[i], dp[i-c]+1);
                }

            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
