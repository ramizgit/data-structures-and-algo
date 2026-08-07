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

        dp[0] = nums[0]; //base case

        for(int i=1; i<n; i++){ //O(n)
            //loop backward from i-1 to i-k and try extending each valid subsequence.
            for (int j = Math.max(0, i - k); j < i; j++){ //O(k)
                dp[i] = Math.max(dp[i], nums[i] + dp[j]);
            }
        }

        return dp[n-1]; //must reach the last index of the array
    }

    //optimized version below using Linear DP + Monotonic Decreasing Deque
    // Time : O(n)
    // Space: O(n)
    /*
    Each index is inserted into the deque exactly once and removed at most once (either from the front when it expires or from the back when it's dominated).
    Therefore, the total number of deque operations across the entire algorithm is O(n), making the overall time complexity O(n).
     */
    public int maxResultViaMonotonicDeque(int[] nums, int k)
    {
        int n = nums.length;

        int[] dp = new int[n]; //dp[i] = maximum score to reach index i

        dp[0] = nums[0]; //base case

        Deque<Integer> monoDecDeque = new ArrayDeque<>(); //monotonic deque over the DP array
        monoDecDeque.offerLast(0); //push first index

        for(int i=1; i<n; i++){ //O(n)

            //remove out of bound indices first
            while(!monoDecDeque.isEmpty() && monoDecDeque.peekFirst() < (i-k)){
                monoDecDeque.pollFirst();
            }

            // Jump from the previous index with the maximum score in the current window.
            dp[i] = nums[i] + dp[monoDecDeque.peekFirst()];

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
