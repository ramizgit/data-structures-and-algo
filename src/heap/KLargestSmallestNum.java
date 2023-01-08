package heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class KLargestSmallestNum {
    public static void main(String[] args)
    {
        int[] arr = {8,1,3,2,6,7,9};

        System.out.println(findKthLargestElementWIthHeapOfSizeK(arr, 3)); //7
        System.out.println(findKthLargestElementWIthHeapOfSizeArray(arr, 3)); //7

        System.out.println(findKthSmallestElementWIthHeapOfSizeK(arr, 3)); //3
    }

    public static int findKthLargestElementWIthHeapOfSizeK(int[] arr, int k)
    {
        //****BETTER APPROACH, TAKES LESS SPACE COMPLEXITY*******

        //TIME COMPLEXITY : [0(n) + nlog(k)]
            //0(n) to travers entire array
            //nlog(k) as we call heapify n times and heap size is k all the time

        Queue<Integer> minheap = new PriorityQueue<>();

        for(int e : arr){
            minheap.add(e);

            if(minheap.size() > k){
                minheap.remove();
            }
        }

        if(!minheap.isEmpty()){
            return minheap.peek();
        }else {
            return -1; //no elelement
        }
    }

    public static int findKthSmallestElementWIthHeapOfSizeK(int[] arr, int k)
    {
        //****BETTER APPROACH, TAKES LESS SPACE COMPLEXITY*******

        //TIME COMPLEXITY : [0(n) + nlog(k)]
            //0(n) to travers entire array
            //nlog(k) as we call heapify n times and heap size is k all the time

        Queue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());

        for(int e : arr){
            maxheap.add(e);

            if(maxheap.size() > k){
                maxheap.remove();
            }
        }

        if(!maxheap.isEmpty()){
            return maxheap.peek();
        }else {
            return -1;//no element
        }
    }

    public static int findKthLargestElementWIthHeapOfSizeArray(int[] arr, int k)
    {
        //****BAD APPROACH, TAKES MORE SPACE COMPLEXITY*****
        Queue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());

        for(int e : arr){
            maxheap.add(e);
        }

        for(int i=0; i<k-1; i++){
            maxheap.remove();
        }

        return maxheap.peek();
    }
}
