package monotonicstack;

import java.util.Stack;

public class NumberOfPeopleVisisble {
    //https://leetcode.com/problems/number-of-visible-people-in-a-queue/description/
    public static void main(String[] args)
    {
        canSeePersonsCount(new int[]{10,6,8,5,11,9});
        canSeePersonsCount(new int[]{5,1,2,3,10});
        canSeePersonsCount(new int[]{5,4,3,2,1});
    }

    private static int[] canSeePersonsCount(int[] heights)
    {
        int n = heights.length;
        int[] output = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=n-1; i>=0; i--){
            int count = 0;
            //monotonic decreasing stack
            while (!stack.empty() && heights[i] >= heights[stack.peek()]){
                stack.pop();
                count++;
            }

            if(!stack.empty()){
                count++;
            }

            output[i] = count;

            stack.push(i);
        }

        return output;
    }
}
