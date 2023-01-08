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

    public static void printSlidingWindowMax(int[] arr, int window)
    {
        Deque<Integer> deque = new LinkedList<>();

        for(int i=0; i<window; i++)
        {
            //keep on removing smaller numbers
            while (!deque.isEmpty() && arr[i] > arr[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.add(i);
        }
        System.out.println(arr[deque.peekFirst()]);

        for(int i=window; i < arr.length; i++ )
        {
            //remove out of boundary index
            if(i - deque.peekFirst() >= window){
                deque.removeFirst();
            }

            //keep on removing smaller numbers
            while (!deque.isEmpty() && arr[i] > arr[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.add(i);
            System.out.println(arr[deque.peekFirst()]);
        }

    }
}
