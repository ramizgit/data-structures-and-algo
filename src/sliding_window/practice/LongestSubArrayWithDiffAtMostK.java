package sliding_window.practice;

import java.util.*;

/*
You are given an array of integers nums and an integer limit.

Return the length of the longest non-empty contiguous subarray such that the absolute difference between any two elements in that subarray is less than or equal to limit.

In other words, for every subarray, the difference between its maximum and minimum elements must satisfy:
\[
\max(subarray)-\min(subarray)\leq limit
\]

Example 1
Input:
nums = [8, 2, 4, 7], limit = 4

Output:
2


Explanation:

The longest valid subarray is [2, 4].

Its maximum is 4 and minimum is 2.

Their difference is 4 - 2 = 2, which is less than or equal to 4.
 */

public class LongestSubArrayWithDiffAtMostK {

    /*
    Operation	Total complexity
    Outer loop	O(n)
    Max deque maintenance	O(n)
    Min deque maintenance	O(n)
    Window shrinking	O(n)
    Answer calculation	O(n)
     */
    public int longestSubarray(int[] nums, int limit)
    {
        int n = nums.length;
        int windowLeft = 0;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int answer = 0;

        for(int windowRight = 0; windowRight < n; windowRight++){

            //max deque operation
            while(!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[windowRight]){
                maxDeque.pollLast();
            }

            maxDeque.offerLast(windowRight);

            //min deque operation
            while(!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[windowRight]){
                minDeque.pollLast();
            }

            minDeque.offerLast(windowRight);

            //shrink window if needed
            while(nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit){

                if (maxDeque.peekFirst() == windowLeft) {
                    maxDeque.pollFirst();
                }

                if (minDeque.peekFirst() == windowLeft) {
                    minDeque.pollFirst();
                }

                windowLeft++;
            }

            //capture answer
            answer = Math.max(answer, windowRight - windowLeft + 1);
        }

        return answer;
    }
}
