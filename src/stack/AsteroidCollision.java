package stack;

import java.util.*;

public class AsteroidCollision {

    //https://leetcode.com/problems/asteroid-collision/description/

    private static int[] asteroidCollision(int[] asteroids)
    {
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i=0; i<asteroids.length; i++){
            int current = asteroids[i];
            boolean alive = true;

            while (alive && !stack.isEmpty() && current < 0 && stack.peek() > 0){
                //collision will happen, stack top is moving right (+) and current is moving left (-)
                if(stack.peek() > -current){
                    alive = false; //current will get destroyed
                } else if (stack.peek() == -current) {
                    //both will get destroyed
                    stack.pop();
                    alive = false;
                }else {
                    stack.pop(); //only top will get destroyed
                }
            }

            if(alive){
                stack.push(current);
            }
        }

        //collect output
        int[] output = new int[stack.size()];
        int i = output.length-1;
        while (!stack.isEmpty()){
            output[i] = stack.pop();
            i--;
        }

        return output;
    }
}
