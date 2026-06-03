package monotonicstack;

import java.util.*;

public class LargestRectangleInHistogram {

    public static int getLargestRectangle(int[] heights)
    {
        /*
        Approach
        For each bar:
            find first smaller on left
            find first smaller on right
            width = rightBoundary - leftBoundary + 1
            area = height * width
         */

        int[] leftArray = computeLeftBoundary(heights);
        int[] rightArray = computeRightBoundary(heights);

        int maxArea = 0;

        for(int i=0; i<heights.length; i++)
        {
            int area = heights[i] * (rightArray[i] - leftArray[i] + 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static int[] computeLeftBoundary(int[] arr)
    {
        //next smaller element in left direction, use monotonic increasing stack
        int[] leftArray = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i=0; i<arr.length; i++){
            while(!stack.isEmpty() && arr[i] <= arr[stack.peek()]){
                stack.pop();
            }

            leftArray[i] = stack.isEmpty() ? 0 : stack.peek()+1;
            stack.push(i);
        }
        return leftArray;
    }

    public static int[] computeRightBoundary(int[] arr)
    {
        //next smaller element in right direction, use monotonic increasing stack
        int[] rightArray = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i=arr.length-1; i>=0; i--){
            while(!stack.isEmpty() && arr[i] <= arr[stack.peek()]){
                stack.pop();
            }

            rightArray[i] = stack.isEmpty() ? arr.length-1 : stack.peek()-1;
            stack.push(i);
        }
        return rightArray;
    }
}
