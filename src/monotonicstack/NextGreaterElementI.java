package monotonicstack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElementI {
    //https://leetcode.com/problems/next-greater-element-i/description/
    public static void main(String[] args)
    {
        //nums1 = [4,1,2], nums2 = [1,3,4,2]
        nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4});
    }

    private static int[] nextGreaterElement(int[] nums1, int[] nums2)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums1.length; i++){
            map.put(nums1[i], i);
        }

        Stack<Integer> stack = new Stack<>();
        int[] output = new int[nums1.length];

        for(int i=nums2.length-1; i>=0; i--){
            while (!stack.empty() && nums2[i] >= nums2[stack.peek()]){
                stack.pop();
            }

            if(map.containsKey(nums2[i])){
                output[map.get(nums2[i])] = stack.empty() ? -1 : nums2[stack.peek()];
            }

            stack.push(i);
        }

        return output;
    }
}
