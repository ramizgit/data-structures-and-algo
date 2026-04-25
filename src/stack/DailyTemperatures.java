package stack;

import java.util.Stack;

public class DailyTemperatures {

    //https://leetcode.com/problems/daily-temperatures/description/

    private static int[] dailyTemperatures(int[] temperatures)
    {
        //input validation
        if(temperatures == null || temperatures.length == 0){
            return new int[0];
        }

        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];

        for(int i=n-1; i>=0; i--){
            //keep on popping from stack till warmer temp found
            while (!stack.empty() && temperatures[i] > temperatures[stack.peek()]){
                stack.pop();
            }

            answer[i] = stack.isEmpty() ? 0 : stack.peek() - i; //num of days diff

            stack.push(i); //push current temp to stack
        }

        return answer;
    }
}
