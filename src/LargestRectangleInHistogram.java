
package stack;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public static void main(String[] args)
    {
        System.out.println("Max area is : "+getLargestRectangle(new int[]{2,1,5,6,2,3})); //10
        System.out.println("Max area is : "+getLargestRectangle(new int[]{3, 2, 4, 5, 3, 6, 2})); //14
        System.out.println("Max area is : "+getLargestRectangle(new int[]{6, 2, 5, 4, 5, 1, 6})); //12
    }

    public static int getLargestRectangle(int[] arr)
    {
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
        //[2,1,5,6,2,3]
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
