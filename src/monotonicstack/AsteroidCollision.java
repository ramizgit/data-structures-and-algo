package monotonicstack;

import java.util.Stack;

public class AsteroidCollision {

    //https://leetcode.com/problems/asteroid-collision/description/

    public static void main(String[] args)
    {
        asteroidCollision(new int[]{-9,10,2,-15});
        asteroidCollision(new int[]{10,2,-5});
        asteroidCollision(new int[]{5,10,-5});
        asteroidCollision(new int[]{5,10,-15,-10});
        asteroidCollision(new int[]{-5,-10,-15,-10,1,2,3});
    }

    private static int[] asteroidCollision(int[] asteroids)
    {
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<asteroids.length; i++){
            int current = asteroids[i];
            boolean destroyed = false;

            while (!stack.empty() && current < 0 && stack.peek() > 0){
                //collision will happen
                int currentAbs = Math.abs(current);
                if(currentAbs < stack.peek()){
                    //current will get destroyed
                    destroyed = true;
                    break;
                } else if (currentAbs == stack.peek()) {
                    //both will get destroyed
                    destroyed = true;
                    stack.pop();
                    break;
                }else {
                    //only top will get destroyed
                    stack.pop();
                }
            }

            if(!destroyed){
                stack.push(current);
            }
        }

        //collect output
        int[] output = new int[stack.size()];
        int i = output.length-1;
        while (!stack.empty()){
            output[i] = stack.pop();
            i--;
        }

        return output;
    }
}
