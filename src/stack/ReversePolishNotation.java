package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReversePolishNotation {
    public static void main(String[] args)
    {
        System.out.println(evalRPN(new String[]{"1","2","+","3","*","4","-"}));
    }

    private static int evalRPN(String[] tokens)
    {
        Deque<Integer> stack = new ArrayDeque<>();

        for(String token : tokens){
            if(isNumber(token)){
                stack.push(Integer.parseInt(token));
            }else{
                int b = stack.pop();
                int a = stack.pop();
                int result = switch (token) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> a / b;
                    default -> throw new IllegalArgumentException("Invalid operator: " + token);
                };

                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static boolean isNumber(String s) {
        // Supports negative numbers as well
        if (s == null || s.isEmpty()) return false;
        if (s.length() > 1 && s.charAt(0) == '-') {
            s = s.substring(1);
        }
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
