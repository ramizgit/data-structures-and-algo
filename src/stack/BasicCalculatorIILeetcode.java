package stack;

import java.util.Stack;

public class BasicCalculatorIILeetcode {
    //https://leetcode.com/problems/basic-calculator-ii/

    public static void main(String[] args)
    {
        System.out.println(calculate("3+2*2")); //7
        System.out.println(calculate("3/2")); //1
        System.out.println(calculate(" 3+5 / 2 ")); //5
        System.out.println(calculate(" 12+10*20+30-10+6/3 ")); //234

    }

    private static int calculate(String s)
    {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int i=0;
        int num = 0;
        char op = '+';

        while(i<s.length())
        {
            if (isDigit(s.charAt(i))){
                num = num * 10 + (s.charAt(i) - '0');
            }

            if( (!isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length()-1){
                switch (op) {
                    case '+' :
                        stack.push(num);
                        break;
                    case '-' :
                        stack.push(-1 * num);
                        break;
                    case '*' :
                        stack.push(stack.pop() * num);
                        break;
                    case '/' :
                        stack.push(stack.pop() / num);
                        break;
                    default:
                        break;
                }

                //reset num
                num = 0;
                //reset operator
                op = s.charAt(i);
            }
            i++;
        }

        //now sum all elements of the stack
        while (!stack.empty()){
            result += stack.pop();
        }

        return result;
    }

    private static boolean isDigit(char ch)
    {
        int n = ch - '0';
        return n >= 0 && n <= 9;
        //return Character.isDigit(ch);
    }
}
