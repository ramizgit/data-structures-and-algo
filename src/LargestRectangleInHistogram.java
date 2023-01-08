import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Stack;

public class LargestRectangleInHistogram { //todo:practice

    public static void main(String[] args)
    {
        System.out.println("Max area is : "+getLargestRectangle(new int[]{3, 2, 4, 5, 3, 6, 2})); //14
        System.out.println("Max area is : "+getLargestRectangle(new int[]{6, 2, 5, 4, 5, 1, 6})); //12
    }

    public static int getLargestRectangle(int[] arr)
    {
        //compute left array
        int[] leftArray = computeLeftArray(arr);
        for(int e : leftArray){
            System.out.print(e+ " ");
        }
        System.out.println();

        //compute right array
        int[] rightArray = computeRightArray(arr);
        for(int e : rightArray){
            System.out.print(e+ " ");
        }
        System.out.println();

        //we have got both left and right array limits
        //now calculate area for each posisition
        int maxArea = 0;

        for(int i=0; i<arr.length; i++)
        {
            int area = arr[i] * (rightArray[i] - leftArray[i] + 1);
            System.out.print(area+ " ");

            maxArea = Math.max(maxArea, area);
            /*if(area > maxArea){
                maxArea = area;
            }*/
        }

        System.out.println();

        return maxArea;
    }

    public static int[] computeLeftArray(int[] arr)
    {
        //calculate left most limit for each element where height is less than current element height
        int[] leftArray = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        leftArray[0] = 0;

        for(int i=1; i<arr.length; i++){
            while(!stack.empty() && arr[i] <= arr[stack.peek()]){
                stack.pop();
            }
            if(stack.empty()){
                leftArray[i] = 0;
            }else{
                leftArray[i] = stack.peek()+1;
            }
            stack.push(i);
        }
        return leftArray;
    }

    public static int[] computeRightArray(int[] arr)
    {
        //calculate right most limit for each element where height is less than current element height
        int[] rightArray = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        stack.push(arr.length-1);
        rightArray[arr.length-1] = arr.length-1;

        for(int i=arr.length-2; i>=0; i--){
            while(!stack.empty() && arr[i] <= arr[stack.peek()]){
                stack.pop();
            }
            if(stack.empty()){
                rightArray[i] = arr.length-1;
            }else{
                rightArray[i] = stack.peek()-1;
            }
            stack.push(i);
        }
        return rightArray;
    }
}
