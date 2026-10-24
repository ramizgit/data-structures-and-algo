package dp.lineardp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class JumpGamevi {

    //https://leetcode.com/problems/jump-game-vi/

    //hint : Linear DP + Monotonic Decreasing Deque

    //important : "This is the same monotonic deque optimization ConstrainedSubsequenceSum. The only difference is that here I cannot start a new path at index i, so I remove the max(0, ...) logic."

    // Time : O(n * k)
    // Space: O(n)
    public int maxResult(int[] nums, int k)
    {
        int n = nums.length;

        int[] dp = new int[n]; //dp[i] = maximum score to reach index i
        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[0] = nums[0]; //base case, starting point of jump

        for(int i=1; i<n; i++){ //O(n)
            //For every index i, try all possible previous jump positions from i-k to i-i and take the one giving the maximum score
            for (int j = Math.max(0, i - k); j < i; j++){ //O(k)
                dp[i] = Math.max(
                        dp[i], //current best
                        nums[i] + dp[j] //jump from j to i
                );
            }
        }

        return dp[n-1]; //must reach the last index of the array
    }

    //optimized version below using Linear DP + Monotonic Decreasing Deque
    // Time : O(n)
    // Space: O(n)
    /*
    The DP transition needs the maximum dp[j] among the previous k indices, which is a sliding window maximum problem.
    Use a monotonic decreasing deque to maintain candidate indices in decreasing order of their DP values, removing expired and dominated indices.
    This optimizes the time complexity from (O(nk)) to (O(n)).
     */
    public int maxResultViaMonotonicDeque(int[] nums, int k)
    {
        int n = nums.length;

        int[] dp = new int[n]; //dp[i] = maximum score to reach index i

        dp[0] = nums[0]; //base case

        Deque<Integer> monoDecDeque = new ArrayDeque<>(); //monotonic deque over the DP array. the deque stores indices, not DP values, but those indices are ordered by their corresponding DP values in descending order.
        monoDecDeque.offerLast(0); //push first index

        for(int i=1; i<n; i++){ //O(n)

            //remove out of bound indices first
            while(!monoDecDeque.isEmpty() && monoDecDeque.peekFirst() < (i-k)){
                monoDecDeque.pollFirst();
            }

            // Jump from the previous index with the maximum score in the current window.
            dp[i] = nums[i] + dp[monoDecDeque.peekFirst()]; //The deque does the maximization, so you don't need Math.max() again.

            //remove smaller DP values from the back, maintain monotonic decreasing queue
            while(!monoDecDeque.isEmpty() && dp[monoDecDeque.peekLast()] <= dp[i]){
                monoDecDeque.pollLast();
            }

            //add current index.
            monoDecDeque.offerLast(i);
        }

        return dp[n-1]; //must reach the last index of the array
    }
}
