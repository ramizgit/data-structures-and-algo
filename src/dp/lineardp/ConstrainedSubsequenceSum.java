package dp.lineardp;

import java.util.*;

public class ConstrainedSubsequenceSum {

    //https://leetcode.com/problems/constrained-subsequence-sum/description/

    // Time : O(n * k)
    // Space: O(n)
    public int constrainedSubsetSum(int[] nums, int k)
    {
        int n = nums.length;

        int[] dp = new int[n]; //dp[i] = maximum subsequence sum ending at i

        dp[0] = nums[0]; //base case
        int answer = dp[0];

        for(int i=1; i<n; i++){ //O(n)

            dp[i] = nums[i];   //start a new subsequence ending at the current index

            //loop backward from i-1 to i-k and try extending each valid subsequence.
            for (int j = Math.max(0, i - k); j < i; j++){ //O(k)

                // Extend only if the previous subsequence contributes positively.
                // Otherwise, start a new subsequence at the current index.
                if (dp[j] > 0) {
                    dp[i] = Math.max(
                            dp[i], //current best
                            nums[i] + dp[j] //take the best subsequence that currently ends at j, and append nums[i] to it
                    );
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    // Time : O(n)
    // Space: O(n)
    /*
    Each index is inserted into the deque exactly once and removed at most once (either from the front when it expires or from the back when it's dominated).
    Therefore, the total number of deque operations across the entire algorithm is O(n), making the overall time complexity O(n).
     */
    public int constrainedSubsetSumViaMonotonicDeque(int[] nums, int k)
    {
        int n = nums.length;

        int[] dp = new int[n]; //dp[i] = maximum subsequence sum ending at i

        dp[0] = nums[0]; //base case
        int answer = dp[0];

        Deque<Integer> monoDecDeque = new ArrayDeque<>(); //monotonic deque over the DP array
        monoDecDeque.offerLast(0); //push first index

        for(int i=1; i<n; i++){ //O(n)

            dp[i] = nums[i]; //start a new subsequence ending at the current index

            //remove out of bound indices first
            while(!monoDecDeque.isEmpty() && monoDecDeque.peekFirst() < (i-k)){
                monoDecDeque.pollFirst();
            }

            //extend the best subsequence in the window.
            if(!monoDecDeque.isEmpty() && dp[monoDecDeque.peekFirst()] > 0){
                dp[i] += dp[monoDecDeque.peekFirst()];
            }

            //remove smaller DP values from the back, maintain monotonic decreasing queue
            while(!monoDecDeque.isEmpty() && dp[monoDecDeque.peekLast()] <= dp[i]){
                monoDecDeque.pollLast();
            }

            //add current index.
            monoDecDeque.offerLast(i);

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}
