package monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {
    //https://leetcode.com/problems/remove-k-digits/description/
    public static void main(String[] args)
    {
        System.out.println(removeKdigits("1432219", 3)); //1219
        System.out.println(removeKdigits("10200", 1)); //200
        System.out.println(removeKdigits("10", 2)); //0
        System.out.println(removeKdigits("54321", 2)); //321
        System.out.println(removeKdigits("12345", 2)); //321
    }

    private static String removeKdigits(String num, int k)
    {
        Deque<Character> stack = new ArrayDeque<>();

        for(char ch : num.toCharArray()){
            while (!stack.isEmpty() && k > 0 && ch < stack.peek()){
                stack.pop();
                k--;
            }
            stack.push(ch);
        }

        //if still something left
        while(!stack.isEmpty() && k > 0){
            stack.pop();
            k--;
        }

        //collect output
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.insert(0, stack.pop());
        }

        // Remove leading zeros
        while (!sb.isEmpty() && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.isEmpty() ? "0" : sb.toString();
    }
}
