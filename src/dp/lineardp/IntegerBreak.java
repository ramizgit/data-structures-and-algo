package consistenthashing.dp.lineardp;

public class IntegerBreak {

    //https://leetcode.com/problems/integer-break/description/

    //todo : practice

    public int integerBreak(int n)
    {
        // DP Idea:
        // For every integer i, try every possible first cut j + (i - j).
        // Keep the first part fixed and decide whether breaking the remaining part further gives a better product.

        int[] dp = new int[n + 1]; //dp[i] = max product obtainable by breaking integer i

        //base case for 0 and 1 - cant break them into at least two positive integers
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) { //compute dp[2], dp[3] .... dp[n]

            //try every possible first break of i, and chooses the one that gives the maximum product.
            for (int j = 1; j < i; j++) {

                int firstPart = j;
                int remainingPart = i - j;

                int bestRemaining = Math.max(
                        remainingPart,  //either keep the remaining part intact,
                        dp[remainingPart] //or break it further if that gives a better product.
                );

                dp[i] = Math.max(
                        dp[i],                  //best product found so far
                        firstPart * bestRemaining //product if we cut i into (firstPart) and (remainingPart)
                );
            }
        }

        return dp[n];
    }
}
