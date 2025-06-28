package monotonicstack;

import java.util.Stack;

public class OneThreeTwoPattern {
    //https://leetcode.com/problems/132-pattern/description/
    public static void main(String[] args)
    {
        System.out.println(find132pattern(new int[]{1,2,3,4}));
        System.out.println(find132pattern(new int[]{3,1,4,2}));
        System.out.println(find132pattern(new int[]{-1,3,2,0}));
    }

    private static boolean find132pattern(int[] nums)
    {
        Stack<Pair> stack = new Stack<>();
        int min = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            while (!stack.empty() && nums[i] >= stack.peek().value){
                stack.pop();
            }

            if(!stack.empty() && nums[i] > stack.peek().min){
                return true;
            }

            stack.push(new Pair(nums[i], min));
            min = Math.min(min, nums[i]);
        }

        return false;
    }
}

class Pair{
    int value;
    int min;

    public Pair(int value, int min) {
        this.value = value;
        this.min = min;
    }
}
