package consistenthashing.array;

import java.util.*;

public class MaximumWidthRamp {

    //https://leetcode.com/problems/maximum-width-ramp/description/

    /*
    Approach :-
    Build a monotonically decreasing stack of indices from left to right.
    Push index i only if nums[i] is smaller than the value at the current stack top.
    These indices represent prefix minima and are the only useful candidates for the left endpoint.
    Scan the array from right to left.

    For each j, while the stack is not empty and:

    nums[stackTop] <= nums[j]

    we found a valid ramp.

    Compute:

    width = j - stackTop

    update the answer, and pop the stack top.

    We can pop because this j is the farthest-right (largest possible) position that can ever give a width for that i.

    Each index is pushed and popped at most once, so:

    Time: O(n)
    Space: O(n)

    The key insight is:

    Any index that is not a new minimum is dominated by an earlier smaller value, so it can never be the best left endpoint of a maximum-width ramp.
     */

    public int maxWidthRamp(int[] nums)
    {
        //input validation
        if(nums == null || nums.length == 0){
            return 0;
        }

        int n = nums.length;

        //build monotonic decreasing stack from left to right
        //Stack<Integer> stack = new Stack<>(); //stack is considered legacy in java
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for(int i=1; i<n; i++){
            if(nums[i] < nums[stack.peek()]){
                stack.push(i);
            }
        }

        //scan array from right to left to find best matching pair
        int max = 0;
        for(int j=n-1; j>=0; j--){
            while(!stack.isEmpty() && nums[j] >= nums[stack.peek()]){
                max = Math.max(max, j - stack.peek()); //possible answer
                stack.pop(); //pop top item to find better match if any
            }
        }

        return max;
    }
}
