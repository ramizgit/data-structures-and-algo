package dp;

import java.util.*;

public class ConstrainedSubsequenceSum {

    //https://leetcode.com/problems/constrained-subsequence-sum/description/

    //Time : O(n)
    public int constrainedSubsetSum(int[] nums, int k)
    {
        int n = nums.length;

        int[] dp = new int[n]; //dp[i] = maximum subsequence sum ending at i

        Arrays.fill(dp, Integer.MIN_VALUE); //since we might have -ve nums in the array

        dp[0] = nums[0]; //base case
        int answer = dp[0];

        for(int i=1; i<n; i++){ //O(n)

            dp[i] = nums[i];   // start a new subsequence

            //try all sub seq from i-k to i-1
            for (int j = Math.max(0, i - k); j < i; j++){ //O(k)

                // Extend only if the previous subsequence contributes positively. Otherwise, starting a new subsequence at i is better.
                if (dp[j] > 0) {
                    // Either keep the current best subsequence ending at i, or extend the previous profitable subsequence ending at j.
                    dp[i] = Math.max(dp[i], nums[i] + dp[j]);
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    //todo : implement in O(n) using monotonic deque
}
