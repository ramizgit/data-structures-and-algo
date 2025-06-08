package stack;

import java.util.Stack;

public class BasicCalculator {

    public static void main(String[] args)
    {
        System.out.println(calculate("10+12-20+40")); //42
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)")); //23
        System.out.println(calculate("123")); //123
    }

    private static int calculate(String s)
    {
        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        int ans = 0;
        int sign = 1;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                //get all digits
                curr = ch - '0';
                while (i < s.length()-1 && Character.isDigit(s.charAt(i+1))){
                    curr = curr * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                curr = curr * sign;
                ans += curr;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                //stack operation
                stack.push(ans);
                stack.push(sign);

                //reset
                ans = 0;
                sign = 1;
            } else if (ch == ')') {
                //stak operation
                int prevSign = stack.pop();
                ans *= prevSign;
                int prevAns = stack.pop();
                ans += prevAns;
            }
        }
        return ans;
    }
}

