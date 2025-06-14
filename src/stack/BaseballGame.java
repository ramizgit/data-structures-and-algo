package stack;

import java.util.Stack;

public class BaseballGame {
    //https://leetcode.com/problems/baseball-game/description/
    public static void main(String[] args)
    {
        System.out.println(calPoints(new String[]{"5","2","C","D","+"})); //30
        System.out.println(calPoints(new String[]{"5","-2","4","C","D","9","+","+"})); //27
        System.out.println(calPoints(new String[]{"1","C"})); //0
    }

    private static int calPoints(String[] operations)
    {
        Stack<Integer> stack = new Stack<>();

        for(String op : operations){
            if(op.equals("C") && !stack.empty()){
                stack.pop();
            }else if(op.equals("D") && !stack.empty()){
                stack.push(2 * stack.peek());
            }else if(op.equals("+") && stack.size() >=2 ){
                int top = stack.pop();
                int secondtop = stack.pop();
                stack.push(secondtop);
                stack.push(top);
                stack.push(top + secondtop);
            }else {
                //integer
                stack.push(Integer.parseInt(op));
            }
        }

        int result = 0;
        while (!stack.empty()){
            result += stack.pop();
        }

        return result;
    }
}
