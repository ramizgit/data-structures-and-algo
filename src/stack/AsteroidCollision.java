package stack;

import java.util.Stack;

public class AsteroidCollision {

    //https://leetcode.com/problems/asteroid-collision/description/

    public static void main(String[] args)
    {
        //[10,2,-5]
        asteroidCollision(new int[]{10,2,-5});
        asteroidCollision(new int[]{5,10,-5});
        asteroidCollision(new int[]{5,10,-15,-10});
        asteroidCollision(new int[]{-5,-10,-15,-10,1,2,3});
    }

    private static int[] asteroidCollision(int[] asteroids)
    {
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<asteroids.length; i++){
            if(!stack.empty() && stack.peek() > 0 && asteroids[i] < 0){
                //collision will happen, take action
                while (!stack.empty()){
                    if(Math.abs(asteroids[i]) < stack.peek()){
                        break;
                    } else {
                        stack.pop();
                    }
                }
                //push if empty stack
                if(stack.empty()){
                    stack.push(asteroids[i]);
                }
            }else {
                stack.push(asteroids[i]);
            }
        }

        int[] output = new int[stack.size()];
        int i = output.length-1;
        while (!stack.empty()){
            output[i] = stack.pop();
            i--;
        }

        return output;
    }
}

