package consistenthashing.dp.knapsack;

public class CoinChangeii {

    //https://leetcode.com/problems/coin-change-ii/description/

    //Return the number of combinations that make up that amount.

    public int change(int amount, int[] coins)
    {

        int[] dp = new int[amount + 1]; //dp[t] = number of combinations that make up amount t, initially all zero

        dp[0] = 1; //base case : 1 way to make amount 0 by choosing none

        for(int coin : coins){
            for(int t = coin; t <= amount; t++){ //forward loop as each coin can be used multiple times (unbounded knapsack)

                dp[t] = dp[t] //existing ways
                        + dp[t - coin]; //new ways by taking the current coin
            }
        }

        return dp[amount];
    }
}
