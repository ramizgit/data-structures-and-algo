package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class OneThreeTwoPattern {
    public static void main(String[] args)
    {
        System.out.println(find132pattern(new int[]{1,2,3,4})); //false
        System.out.println(find132pattern(new int[]{3,1,4,2})); //true
        System.out.println(find132pattern(new int[]{3, 1, 2, 0, 4})); //false
    }

    private static boolean find132pattern(int[] nums)
    {
        //use a stack
        //use stack top to store j
        //keep track of min so far for i
        //current element will represent k
        //solution is found when k < j and k > i

        Deque<ValueMinPair> stack = new ArrayDeque<>();
        int min = Integer.MAX_VALUE;

        for(int num : nums){
            //monotonic decreasing stack
            while(!stack.isEmpty() && num >= stack.peek().val){
                stack.pop();
            }

            if(!stack.isEmpty() && num < stack.peek().val && num > stack.peek().min){
                //answer found
                return true;
            }

            stack.push(new ValueMinPair(num, min));

            min = Math.min(min, num);
        }

        return false;
    }
}

class ValueMinPair{
    int val;
    int min;

    public ValueMinPair(int val, int min) {
        this.val = val;
        this.min = min;
    }
}

