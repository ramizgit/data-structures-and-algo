package dp.knapsack;

/*
Your task is to count the number of ways to construct sum n by throwing a dice one or more times. Each throw produces an outcome between 1 and  6.
For example, if n=3, there are 4 ways:

1+1+1
1+2
2+1
3

Input
The only input line has an integer n.
Output
Print the number of ways modulo 10^9+7.
Constraints

1 \le n \le 10^6

Example
Input:
3

Output:
4
 */

public class DiceCombinations {

    //https://cses.fi/problemset/task/1633

    //Pattern : Unbounded Counting DP (Permutations). Order matters.

    //todo : practice

    /*
    why loop order matters:-

    coins = {1,2}, target = 3

    coin → sum
    ---------
    1+1+1
    1+2
    = 2 combinations

    sum → coin
    ---------
    1+1+1
    1+2
    2+1
    = 3 permutations
     */

    public static int countWays(int n) {

        int[] dp = new int[n + 1]; // dp[i] = number of ordered ways to make sum i

        dp[0] = 1; // Base case: one way to make sum 0 (choose nothing)

        // Build answers from smaller sums to larger sums
        // sum outer:
        // Compute answers for each target sum by trying every possible LAST coin.
        // Different last coins generate different sequences (e.g. 1+2 and 2+1),
        // so permutations (order matters) are counted.
        for (int sum = 1; sum <= n; sum++) {

            // Try every possible last dice roll
            for (int dice = 1; dice <= 6; dice++) {

                if (sum >= dice) {
                    dp[sum] = dp[sum] + dp[sum - dice];
                }
            }
        }

        return dp[n];
    }

    //similar problem below

    //https://cses.fi/problemset/task/1635
    //Coin Combinations I

    public static int countCoinWays(int[] coins, int target) {

        int[] dp = new int[target + 1]; // dp[i] = number of ordered ways to make sum i

        dp[0] = 1; // Base case: one way to make sum 0 (choose nothing)

        // Build answers from smaller sums to larger sums
        // coin outer:
        // Process one coin completely before moving to the next.
        // This builds solutions in a fixed coin order, so {1,2} and {2,1}
        // are treated as the same combination and counted only once.
        for (int sum = 1; sum <= target; sum++) {
            for (int coin : coins) {
                if (sum >= coin)
                    dp[sum] = dp[sum] + dp[sum - coin];
            }
        }

        return dp[target];
    }

}

/*
Why does sum → coin naturally do this?
for (int sum = 1; sum <= target; sum++) {
    for (int coin : coins) {
        if (sum >= coin)
            dp[sum] += dp[sum - coin];
    }
}

When you're computing dp[3]:

Try coin = 1 → append 1 to every sequence in dp[2].
Try coin = 2 → append 2 to every sequence in dp[1].

Since appending 1 and appending 2 produce different sequences, both 1+2 and 2+1 are counted.

Why doesn't this happen with coin → sum?

With:

for (coin)
    for (sum)

you first process all sequences using coin 1, then later extend them with coin 2.

When processing coin 2, you're effectively only creating sequences that end with 2 after all smaller coins have already been considered.
You never go back and append a 1 after a 2, so 2+1 is never generated separately.
 */