package sliding_window;

import java.util.*;

public class NumOfSubarrayWithCostK {

    //https://leetcode.com/problems/count-subarrays-with-cost-less-than-or-equal-to-k/description/

    /*
    given an input arr, find num of subarrays that have cost <=k, cost is defined as (max - min) * length of subarray
     */

     /*
    NOTE:

    if questions asks to find num of subarrays with cost exactly k, then call below

    return numOfSubarrWithCostAtMostK(arr, k) - numOfSubarrWithCostAtMostK(arr, k - 1);

    exactly(K) = atMost(K) - atMost(K - 1)
     */

    public long numOfSubarrWithCostK(int[] arr, int k)
    {
        int windowStart = 0;
        long answer = 0;
        Deque<Integer> maxQueue = new ArrayDeque<>(); //monotonically decreasing deque
        Deque<Integer> minQueue = new ArrayDeque<>(); //monotonically increasing deque

        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++){

            int num = arr[windowEnd];

            while(!maxQueue.isEmpty() && arr[maxQueue.peekLast()] < num){
                maxQueue.pollLast();
            }
            maxQueue.offerLast(windowEnd);

            while(!minQueue.isEmpty() && arr[minQueue.peekLast()] > num){
                minQueue.pollLast();
            }
            minQueue.offerLast(windowEnd);

            long cost = (long)(arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()]) * (windowEnd - windowStart + 1);

            while(cost > k){ //shrink window

                if (maxQueue.peekFirst() == windowStart) {
                    maxQueue.pollFirst();
                }
                if (minQueue.peekFirst() == windowStart) {
                    minQueue.pollFirst();
                }

                windowStart++;

                // recompute cost after shrinking
                cost = (long)(arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()]) * (windowEnd - windowStart + 1);
            }

            //capture answer
            answer += windowEnd - windowStart + 1;
        }

        return answer;
    }
}
