package stack;

import java.util.Stack;

public class DailyTemperatures {
    //https://leetcode.com/problems/daily-temperatures/description/
    public static void main(String[] args)
    {
        dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});//[1,1,4,2,1,1,0,0]
        dailyTemperatures(new int[]{30,40,50,60});//[1,1,1,0]
        dailyTemperatures(new int[]{30,60,90});//[1,1,0]
    }

    private static int[] dailyTemperatures(int[] temperatures)
    {
        Stack<Integer> stack = new Stack<>();
        int[] output = new int[temperatures.length];

        for(int i=temperatures.length-1; i>=0; i--){
            while (!stack.empty() && temperatures[i] > temperatures[stack.peek()]){
                stack.pop();
            }

            if(stack.empty()){
                output[i] = 0;
            }else {
                output[i] = stack.peek() - i;
            }
            stack.push(i);
        }

        return output;
    }
}

