package consistenthashing.dp.knapsack;

public class LastStoneWeightii {

    //https://leetcode.com/problems/last-stone-weight-ii/description/

    /*
    A heap is not suitable because the problem does not require picking the two "heaviest" stones — it allows choosing any two stones.
    Since there are many possible sequences of smash operations, a greedy heap explores only one sequence, which may not be optimal.
    We instead use DP to consider all possible partitions of the stones and minimize the final remaining weight.
     */

    /*
    Approach:

    The final remaining stone weight is equivalent to partitioning the stones into
    two groups such that the difference between their sums is minimized.

    1. Let totalSum be the sum of all stones.
    2. The ideal partition is as close as possible to totalSum / 2.
    3. Use 0/1 Knapsack DP where:
          dp[t] = true if a subset with sum t can be formed.
    4. Process each stone once (backward iteration) to compute all possible subset sums.
    5. Find the largest achievable subset sum <= totalSum / 2.
    6. The answer is:
          (totalSum - subsetSum) - subsetSum
        which is the minimum possible remaining stone weight.

    Time : O(n * totalSum)
    Space: O(totalSum)
    */

    public int lastStoneWeightII(int[] stones)
    {
        int totalSum = 0;

        for (int stone : stones) {
            totalSum += stone;
        }

        int target = totalSum / 2;

        boolean[] dp = new boolean[target + 1]; // dp[t] = true if some subset forms sum t

        dp[0] = true; //base case, 0 sum is always possible by picking no element

        for (int stone : stones) {
            for (int t = target; t >= stone; t--) { //backward loop since each stone can be used at most once

                dp[t] = dp[t]              //don't pick current stone
                        || dp[t - stone];  //pick current stone
            }
        }

        //find the subset sum closest to taget (totalSum / 2)
        for (int subsetSum = target; subsetSum >= 0; subsetSum--) {

            if (dp[subsetSum]) {

                int subset1 = subsetSum; //one partition
                int subset2 = totalSum - subset1; //the other partition

                return subset2 - subset1; //remaining stone is the difference between the two partition sums.
            }
        }

        return 0;
    }
}
