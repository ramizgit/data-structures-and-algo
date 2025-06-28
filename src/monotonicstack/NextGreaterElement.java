package monotonicstack;

import java.util.Stack;

public class NextGreaterElement {

    public static void main(String[] args)
    {
        nextGreater(new int[]{1,2,5,9,7});
        nextGreater(new int[]{1,2,5,9,9,7});

        previousGreater(new int[]{1,2,5,9,7});
        previousGreater(new int[]{10,2,5,9,7});

        nextSmaller(new int[]{1,2,5,9,7});
    }

    /* Monotonic strictly decreasing stack */
    private static int[] nextGreater(int[] arr)
    {
        int n = arr.length;
        int[] output = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=n-1; i>=0; i--){
            while (!stack.empty() && arr[i] >= arr[stack.peek()]){
                stack.pop();
            }

            output[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return output;
    }

    /* Monotonic strictly decreasing stack */
    private static int[] previousGreater(int[] arr)
    {
        int n = arr.length;;
        int[] output = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<=n-1; i++){
            while (!stack.empty() && arr[i] >= arr[stack.peek()]){
                stack.pop();
            }

            output[i] = stack.empty() ? -1 : stack.peek();

            stack.add(i);
        }

        return output;
    }

    /* Monotonic strictly increasing stack */
    private static int[] nextSmaller(int[] arr)
    {
        int n = arr.length;;
        int[] output = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=n-1; i>=0; i--){
            while (!stack.empty() && arr[i] <= arr[stack.peek()]){
                stack.pop();
            }

            output[i] = stack.empty() ? -1 : stack.peek();

            stack.add(i);
        }

        return output;
    }

    /* Monotonic strictly increasing stack */
    private static int[] previousSmaller(int[] arr)
    {
        int n = arr.length;;
        int[] output = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<=n-1; i++){
            while (!stack.empty() && arr[i] <= arr[stack.peek()]){
                stack.pop();
            }

            output[i] = stack.empty() ? -1 : stack.peek();

            stack.add(i);
        }

        return output;
    }
}
