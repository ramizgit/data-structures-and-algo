package consistenthashing.dp.knapsack;

public class LastStoneWeightii {

    //https://leetcode.com/problems/last-stone-weight-ii/description/

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
