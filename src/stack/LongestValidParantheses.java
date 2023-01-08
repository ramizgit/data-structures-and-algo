package stack;

import java.util.Stack;

public class LongestValidParantheses {
    public static void main(String[] args)
    {
        System.out.println(getLongestValidParantheses("(()"));
        System.out.println(getLongestValidParantheses(")()())"));
        System.out.println(getLongestValidParantheses(")(()))"));
        System.out.println(getLongestValidParantheses(""));
    }

    public static int getLongestValidParantheses(String input)
    {
        Stack<Character> stack = new Stack<>();
        int output = 0;

        for(int i=0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (c == '(') {
                stack.push(c);
            }
            else {
                if(!stack.empty()){
                    if(stack.peek() == '('){
                        output +=2;
                        stack.pop();
                    }
                }
            }
        }
        return output;
    }
}
