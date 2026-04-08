package dp;

import java.util.*;

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
                if(i >= c){
                    dp[i] = Math.min(dp[i], 1 + dp[i-c]);
                }

            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    //NOTE : ALSO PRINTS COINS USED
    private static int minCoinChangeAlongWithCoinsUsed(int[] coins, int amount)
    {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;

        int[] choice = new int[amount+1];

        for(int i=1; i<=amount; i++){
            for(int c : coins){
                if((i >= c) && (1 + dp[i-c] < dp[i])){
                    //dp[i] = Math.min(dp[i], 1 + dp[i-c]);
                    dp[i] = 1 + dp[i-c];
                    choice[i] = c;
                }

            }
        }

        //PRINT COINS
        int curr = amount;
        List<Integer> coinsUsed = new ArrayList<>();

        while(curr > 0){
            int coin = choice[curr];
            curr -= coin;
            coinsUsed.add(coin);
        }

        System.out.println("coins used : " + coinsUsed);

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
