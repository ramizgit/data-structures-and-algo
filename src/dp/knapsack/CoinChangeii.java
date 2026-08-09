package dp.knapsack;

public class CoinChangeii {

    //https://leetcode.com/problems/coin-change-ii/description/

    //Return the number of combinations that make up that amount. Order DOES NOT matter.

    //Pattern : Unbounded Counting DP (Combinations)

    /*
    coins = [1, 2]
    amount = 3

    coin     0    1    2      3
    0        1    0    0      0
    1             1     1+1   1+1+1
    2                   2      2+1

     */

    public int change(int amount, int[] coins)
    {
        int[] dp = new int[amount + 1]; //dp[t] = number of combinations that make up amount t

        dp[0] = 1; //base case : 1 way to make amount 0 by choosing none

        for(int coin : coins){
            for(int t = coin; t <= amount; t++){ //forward loop as each coin can be used multiple times (unbounded knapsack)

                dp[t] = dp[t] //existing ways using previous coins
                        + dp[t - coin]; //new ways by taking this new coin
            }
        }

        return dp[amount];
    }
}

/*
Start
Amount t	0	1	2	3
dp	1	0	0	0

dp[0] = 1 → one way to make 0: choose nothing.

Process coin 1
t	Calculation	dp[t]	Combination added
1	0 + dp[0]	1	1
2	0 + dp[1]	1	1+1
3	0 + dp[2]	1	1+1+1

Now:

Amount	0	1	2	3
dp	1	1	1	1
Process coin 2
t	Calculation	dp[t]	Meaning
2	1 + dp[0]	2	existing 1+1 + new 2
3	1 + dp[1]	2	existing 1+1+1 + new 1+2

Final:

Amount	0	1	2	3
dp	1	1	2	2
 */


/*
|                | Coin Change I | Coin Change II         |
| -------------- | ------------- | ---------------------- |
| LeetCode       | 322           | 518                    |
| Goal           | Minimum coins | Number of combinations |
| Order matters? | ❌ No          | ❌ No                   |
| DP operation   | `min`         | `+`                    |
| Typical loops  | coin → amount | coin → amount          |
| DP state       | min coins     | number of combinations |

 */