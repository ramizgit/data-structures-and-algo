package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    //https://leetcode.com/problems/valid-parentheses/description/
    public static void main(String[] args)
    {
        System.out.println(isValid("()")); //true
        System.out.println(isValid("()[]{}")); //true
        System.out.println(isValid("()[]{]")); //false
        System.out.println(isValid("([{}])")); //true
        System.out.println(isValid("){}")); //false
        System.out.println(isValid("({[")); //false
    }

    private static boolean isValid(String s)
    {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> parenmap = new HashMap<>();
        parenmap.put(')', '(');
        parenmap.put('}', '{');
        parenmap.put(']', '[');

        for(char ch : s.toCharArray()){
            if(parenmap.containsValue(ch)){
                //opening parentheses
                stack.push(ch);
            }else if(parenmap.containsKey(ch)){
                //closing parentheses
                if(stack.empty() || parenmap.get(ch) != stack.pop()){
                    return false;
                }
            }
        }

        //stack should be empty at the end if valid parentheses
        return stack.empty();
    }
}
