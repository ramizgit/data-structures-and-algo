package monotonicstack;

import java.util.Stack;

public class NextGreaterElementII {
    //https://leetcode.com/problems/next-greater-element-ii/description/
    public static void main(String[] args)
    {
        nextGreaterElements(new int[]{1, 2, 1});
        nextGreaterElements(new int[]{1,2,3,4,3});
    }

    private static int[] nextGreaterElements(int[] nums)
    {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] output = new int[n];

        for(int i=2*n-1; i>=0 ; i--){
            int idx = i % n;
            while (!stack.empty() && nums[idx] >= nums[stack.peek()]){
                stack.pop();
            }

            output[idx] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(idx);
        }

        return output;
    }
}
