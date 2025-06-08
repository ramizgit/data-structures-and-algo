package stack;

import java.util.Stack;

public class DecodeString {
    //https://leetcode.com/problems/decode-string/description/

    public static void main(String[] args)
    {
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[cd]]"));
    }

    private static String decodeString(String s)
    {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){
            if(ch != ']'){
                stack.push(ch);
            }else {

                //step 1 : get char seq to repeat
                StringBuilder sb = new StringBuilder();
                while (stack.peek() != '['){
                    sb.append(stack.pop());
                }
                String substr = sb.reverse().toString();
                stack.pop();

                //step 2 : get count digit to repeat
                StringBuilder countCh = new StringBuilder();
                while (!stack.empty() && Character.isDigit(stack.peek())){
                    countCh.append(stack.pop());
                }
                int count = Integer.parseInt(countCh.reverse().toString());

                //step 3 : create repeated sub string
                StringBuilder repeatSubstr = new StringBuilder();
                while (count >0){
                    repeatSubstr.append(substr);
                    count--;
                }

                //step 4 : push back repeated sub str to the stack
                for(char ch2 : repeatSubstr.toString().toCharArray()){
                    stack.push(ch2);
                }
            }
        }

        //step 5 : collect final result
        StringBuilder result = new StringBuilder();
        while (!stack.empty()){
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }
}

