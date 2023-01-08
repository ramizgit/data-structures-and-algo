package numbers;

import java.util.Stack;

public class RemoveKDigitsToGetMinNum {
    public static void main(String[] args)
    {
        System.out.println(getMin("14301620", 4)); //120
        System.out.println(getMin("1230987", 2)); //10987
        System.out.println(getMin("1230987", 3)); //987

    }

    public static String getMin(String num, int k)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(num.charAt(0) - '0');

        for(int i=1; i<num.length(); i++){
            int nextdigit = num.charAt(i) - '0';
            while (!stack.empty() && nextdigit < stack.peek() && k > 0){
                stack.pop();
                k--;
            }

            stack.push(nextdigit);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.empty()){
            if(stack.size() == 1 && stack.peek() == 0){
                break;
            }else {
                sb.append(stack.pop());
            }
        }

        return sb.reverse().toString();
    }
}
