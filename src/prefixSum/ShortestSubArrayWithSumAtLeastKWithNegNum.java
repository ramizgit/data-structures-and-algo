package prefixSum;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubArrayWithSumAtLeastKWithNegNum {

    //https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/

    public static int shortestSubArray(int[] arr, int targetSum)
    {
        //populate prefix sum array
        int[] prefixSum = new int[arr.length+1];
        prefixSum[0] = 0;

        for(int i=1; i<prefixSum.length; i++){
            prefixSum[i] = prefixSum[i-1] + arr[i-1];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int minSize = Integer.MAX_VALUE;

        for(int i=0; i<prefixSum.length; i++){

            //1. Shrink from front
            //decrease window if sum is greater than target, and capture min same time
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= targetSum){
                minSize = Math.min(minSize, i - deque.peekFirst()); //possible answer
                deque.removeFirst();
            }

            //2. Remove dominated prefixes from back, maintain monotonic increasing queue
            //increase widow, remove from back to maintain increasing order considering -ve numbers
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]){
                deque.removeLast();
            }

            //3. Add current
            deque.addLast(i);
        }

        return minSize == Integer.MAX_VALUE ? -1 : minSize;
    }
}
