package subarray;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestSubArrayWithSumAtLeastKWithNegNum {

    public static void main(String[] args){
        //array with NEGATIVE numbers
        int[] arr = {2,7,3,-8,4,10};
        System.out.println(shortestSubArray(arr, 12));
    }

    public static int shortestSubArray(int[] arr, int targetSum)
    {
        //populate prefix sum array
        int[] prefixSum = new int[arr.length+1];
        prefixSum[0] = 0;
        for(int i=1; i<prefixSum.length; i++){
            prefixSum[i] = prefixSum[i-1]+arr[i-1];
        }

        Deque<Integer> dequeue = new LinkedList<>();
        int minSize = Integer.MAX_VALUE;

        for(int i=0; i<prefixSum.length; i++){

            //decrease window if sum is greater than target, and capture min same time
            while (!dequeue.isEmpty() && prefixSum[i] - prefixSum[dequeue.peekFirst()] >= targetSum){
                minSize = Math.min(minSize, i-dequeue.peekFirst());
                dequeue.removeFirst();
            }

            //increase widow, remove from back to maintain increasing order considering -ve numbers
            while (!dequeue.isEmpty() && prefixSum[i] <= prefixSum[dequeue.peekLast()]){
                dequeue.removeLast();
            }

            dequeue.addLast(i);
        }

        return minSize == Integer.MAX_VALUE ? -1 : minSize;
    }
}
