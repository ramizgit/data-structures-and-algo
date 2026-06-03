package monotonicstack;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public static int getLargestRectangle(int[] arr)
    {
        /*
        Approach
        For each bar:
            find first smaller on left
            find first smaller on right
            width = rightBoundary - leftBoundary + 1
            area = height * width
         */

        int[] leftArray = computeLeftArray(arr);
        int[] rightArray = computeRightArray(arr);

        int maxArea = 0;

        for(int i=0; i<arr.length; i++)
        {
            int area = arr[i] * (rightArray[i] - leftArray[i] + 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static int[] computeLeftArray(int[] arr)
    {
        //next smaller element in left direction, use monotonic increasing stack
        int[] leftArray = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<arr.length; i++){
            while(!stack.empty() && arr[i] <= arr[stack.peek()]){
                stack.pop();
            }

            leftArray[i] = stack.empty() ? 0 : stack.peek()+1;
            stack.push(i);
        }
        return leftArray;
    }

    public static int[] computeRightArray(int[] arr)
    {
        //next smaller element in right direction, use monotonic increasing stack
        int[] rightArray = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=arr.length-1; i>=0; i--){
            while(!stack.empty() && arr[i] <= arr[stack.peek()]){
                stack.pop();
            }

            rightArray[i] = stack.empty() ? arr.length-1 : stack.peek()-1;
            stack.push(i);
        }
        return rightArray;
    }
}
