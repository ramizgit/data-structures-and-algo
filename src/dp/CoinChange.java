package dp;

import java.util.*;

public class CoinChange {
    //https://leetcode.com/problems/coin-change/description/

    private static int minCoinChange(int[] coins, int amount)
    {
        int[] dp = new int[amount+1]; // dp[i] = minimum coins needed to make amount i

        Arrays.fill(dp,amount+1); //initalize with some large value

        dp[0] = 0; //base case - 0 coins needed to make amount 0

        for(int c : coins){
            for(int i=c; i<=amount; i++){ //forward loop as coins can be reused
                dp[i] = Math.min(
                        dp[i], //dont pick
                        1 + dp[i-c] //pick
                );
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    private static int minCoinChange2(int[] coins, int amount)
    {
        int[] dp = new int[amount+1]; // dp[i] = minimum coins needed to make amount i

        Arrays.fill(dp,amount+1); //initalize with some large value

        dp[0] = 0; //base case - 0 coins needed to make amount 0

        for(int i=1; i<=amount; i++){ // build answers from smaller amounts till the target amount
            for(int c : coins){ // try every coin
                if(i >= c){
                    dp[i] = Math.min(
                            dp[i], //dont pick
                            1 + dp[i-c] //pick
                    );
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
        dp[0] = 0; //base case

        int[] choice = new int[amount+1];

        for(int i=1; i<=amount; i++){
            for(int c : coins){
                if( (i >= c) && ( (1 + dp[i-c]) < dp[i]) ){
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
