package array;

import java.util.*;

public class SlidingWindowMaxArray {
    public static void main(String[] args)
    {
        //using dequeue
        printSlidingWindowMax(new int[]{12,13,8,2,6,2,5,8}, 3);
        System.out.println("=============");

        //using max heap
        printSlidingWindowMaxUsingMaxHeap(new int[]{12,13,8,2,6,2,5,8}, 3);
    }

    public static void printSlidingWindowMax(int[] arr, int window)
    {
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for(int i=0; i<arr.length; i++){

            //remove frist element if out of window range
            if(!deque.isEmpty() && (i - deque.peekFirst()) >= window){
                deque.pollFirst();
            }

            //keep on removing last smaller elements
            while (!deque.isEmpty() && arr[i] > arr[deque.peekLast()]){
                deque.pollLast();
            }

            //add to the queue
            deque.offer(i);

            //collect result
            if(i >= window-1){
                result.add(arr[deque.peekFirst()]);
            }
        }

        System.out.println(result);
    }

    public static void printSlidingWindowMaxUsingMaxHeap(int[] arr, int window)
    {
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int p=0;

        for(int i=0; i<window; i++)
        {
            maxHeap.add(arr[i]);
        }
        System.out.println(maxHeap.peek());
        maxHeap.remove(arr[p++]);

        for(int i=window; i < arr.length; i++ ){
            maxHeap.add(arr[i]);
            System.out.println(maxHeap.peek());
            maxHeap.remove(arr[p++]);
        }
    }    
}
