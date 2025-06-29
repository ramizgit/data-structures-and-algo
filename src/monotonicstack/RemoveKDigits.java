package monotonicstack;

import java.util.Stack;

public class RemoveKDigits {
    //https://leetcode.com/problems/remove-k-digits/description/
    public static void main(String[] args)
    {
        System.out.println(removeKdigits("1432219", 3)); //1219
        System.out.println(removeKdigits("10200", 1)); //200
        System.out.println(removeKdigits("10", 2)); //0
    }

    private static String removeKdigits(String num, int k)
    {
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<num.length(); i++){
            int curr = num.charAt(i) - '0';

            //monotonic increasing (non-decreasing) stack
            while (!stack.empty() && curr < stack.peek() && k > 0){
                stack.pop();
                k--;
            }

            stack.push(curr);
        }

        //collect output
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
