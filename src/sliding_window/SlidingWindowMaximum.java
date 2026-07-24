package sliding_window;

import java.util.*;

public class SlidingWindowMaximum {

    //https://leetcode.com/problems/sliding-window-maximum/description/

    public int[] maxSlidingWindow(int[] nums, int k)
    {
        //input validation
        if(nums == null || nums.length == 0){
            return new int[0];
        }

        if(k <= 0){
            return new int[0];
        }

        //edge case where window size is 1
        if(k == 1){
            return nums;
        }

        int windowStart = 0;
        int[] answer = new int[nums.length - k + 1];
        int idx = 0;
        Deque<Integer> deque = new ArrayDeque<>(); //monotonic decreasing queue, ensures head is always max

        for(int windowEnd = 0; windowEnd < nums.length; windowEnd++){

            //expand window
            while(!deque.isEmpty() && nums[windowEnd] >= nums[deque.peekLast()]){ //maintain decreasing deque
                deque.pollLast();
            }

            deque.offerLast(windowEnd);

            //proceed only when window size is k
            if(windowEnd - windowStart + 1 < k){
                continue;
            }

            //window size is k, collect result
            answer[idx++] = nums[deque.peekFirst()];

            //shrink window
            windowStart++;

            //remove indices outside the current window
            while(!deque.isEmpty() && deque.peekFirst() < windowStart) {
                deque.pollFirst();
            }
        }

        return answer;
    }
}
