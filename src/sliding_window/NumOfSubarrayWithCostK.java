package slidingWindow;

import java.util.*;

public class NumOfSubarrayWithCostK {

    /*
    given an input arr, find num of subarrays that have cost <=k, cost is defined as (max - min) * length of subarray
     */

    public int numOfSubarrWithCostK(int[] arr, int k)
    {
        int left = 0;
        int right = 0;
        int answer = 0;
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();

        while(right < arr.length){
            int num = arr[right];

            while(!maxQueue.isEmpty() && arr[maxQueue.peekLast()] < num){
                maxQueue.pollLast();
            }
            maxQueue.offer(right);

            while(!minQueue.isEmpty() && arr[minQueue.peekLast()] > num){
                minQueue.pollLast();
            }
            minQueue.offer(right);

            long cost = (long)(arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()]) * (right - left + 1);

            while(cost > k){ //shrink window

                if (maxQueue.peekFirst() == left) {
                    maxQueue.pollFirst();
                }
                if (minQueue.peekFirst() == left) {
                    minQueue.pollFirst();
                }

                left++;

                // recompute cost after shrinking
                cost = (long)(arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()]) * (right - left + 1);
            }

            //capture answer
            answer += right - left + 1;

            right++;
        }

        return answer;
    }
}
