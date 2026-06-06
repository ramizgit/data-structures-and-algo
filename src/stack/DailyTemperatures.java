package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {

    //https://leetcode.com/problems/daily-temperatures/description/

    public int[] dailyTemperatures(int[] temperatures)
    {
        //input validation
        if(temperatures == null || temperatures.length == 0){
            return new int[0];
        }

        int n = temperatures.length;
        Deque<Integer> stack = new ArrayDeque<>(); //monotonic decreasing stack
        int[] result = new int[n];

        for(int i=n-1; i>=0; i--){
            //keep on popping from stack till warmer temp found
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]){
                stack.pop();
            }

            result[i] = stack.isEmpty() ? 0 : stack.peek() - i; //num of days diff

            stack.push(i); //push current temp to stack
        }

        return result;
    }
}
