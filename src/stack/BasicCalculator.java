package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    public static void main(String[] args)
    {
        System.out.println(calculate("10+12-20+40")); //42
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)")); //23
        System.out.println(calculate("123")); //123
        System.out.println(calculate("-2+3")); //123
    }

    private static int calculate(String s)
    {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int sign = 1;
        int num = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(Character.isDigit(ch)){
                //get all digits
                num = ch - '0';
                while(i<s.length()-1 && Character.isDigit(s.charAt(i+1))){
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                num *= sign;
                result += num;
            }else if(ch == '+'){
                sign = 1;
            }else if(ch == '-'){
                sign = -1;
            }else if(ch == '('){
                //push into stack
                stack.push(result);
                stack.push(sign);

                //reset
                result = 0;
                sign = 1;
            }else if(ch == ')'){
                //add to previous result
                int prevSign = stack.pop();
                int prevResult = stack.pop();
                result *= prevSign;
                result += prevResult;
            }
        }

        return result;
    }
}
