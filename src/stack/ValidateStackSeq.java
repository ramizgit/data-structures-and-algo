package stack;

import java.util.Stack;

public class ValidateStackSeq {

    //https://leetcode.com/problems/validate-stack-sequences/description/

    public static void main(String[] args)
    {
        System.out.println(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1})); //true
        System.out.println(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2})); //false
    }

    private static boolean validateStackSequences(int[] pushed, int[] popped)
    {
        Stack<Integer> stack = new Stack<>();

        int j = 0;
        for(int i=0; i<pushed.length; i++){
            stack.push(pushed[i]);

            while (j < popped.length && !stack.empty() && popped[j] == stack.peek()){
                stack.pop();
                j++;
            }
        }

        return stack.empty();
    }
}

