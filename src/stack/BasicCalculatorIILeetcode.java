package stack;

import java.util.Stack;

public class BasicCalculatorII {

    public static void main(String[] args)
    {
        System.out.println(calculate("3+2*2")); //7
        System.out.println(calculate("3-4+2*2")); //3
        System.out.println(calculate("3-4+2/2")); //0
        System.out.println(calculate(" 3+5 / 2 ")); //5
    }

    private static int calculate(String s)
    {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int curr = 0;
        //int sign = 1;
        char operator = '+';

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(Character.isDigit(ch)) {
                //get all digits
                curr = ch - '0';
                while (i < s.length()-1 && Character.isDigit(s.charAt(i+1))){
                    curr = curr * 10 + (s.charAt(i+1) - '0');
                    i++;
                }

                if(operator == '+'){
                    stack.push(curr);
                } else if (operator == '-') {
                    stack.push(-1 * curr);
                } else if (operator == '*') {
                    stack.push(stack.pop() * curr);
                } else if (operator == '/') {
                    stack.push(stack.pop() / curr);
                }
            }else {
                operator = ch;
            }
        }

        while (!stack.empty()){
            ans += stack.pop();
        }

        return ans;
    }
}
