package stack;

import java.util.Stack;

public class MinimumRemoveToMakeValidParanthesis {

    public static void main(String[] args)
    {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)")); //"lee(t(c)o)de"
        System.out.println(minRemoveToMakeValid("a)b(c)d")); //"ab(c)d"
        System.out.println(minRemoveToMakeValid("))((")); //""

    }

    private static String minRemoveToMakeValid(String input)
    {
        Stack<Integer> stack = new Stack<>();

        //use stack to identify wrong paranthesis indices
        stackOperation(input, stack);

        //now stack contains only invalid, iterate over the string and remove invalid chars
        StringBuilder sb = new StringBuilder();

        for(int i=input.length()-1; i>=0; i--){
            if(stack.empty() || stack.peek() != i){
                sb.append(input.charAt(i));
            }else {
                stack.pop();
            }
        }

        return sb.reverse().toString();
    }

    private static void stackOperation(String input, Stack<Integer> stack)
    {
        for(int i=0; i<input.length(); i++){
            char ch = input.charAt(i);
            switch (ch){
                case '(' :
                    stack.push(i);
                    break;
                case ')' :
                    if(!stack.empty() && input.charAt(stack.peek()) == '('){
                        stack.pop();
                    }else {
                        stack.push(i);
                    }
                    break;
                default:
                    break;
            }

        }
    }
}