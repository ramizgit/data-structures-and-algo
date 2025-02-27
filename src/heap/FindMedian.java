package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedian {

    private static PriorityQueue<Integer> minheap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args)
    {
        int[] stream = {5, 15, 1, 3, 8, 7, 9};

        for (int num : stream) {
            add(num);
            System.out.println("Current Median: " + findMedian());
        }

    }

    private static void add(int num)
    {
        if(maxheap.isEmpty() || num <= maxheap.peek()){
            maxheap.add(num);
        }else{
            minheap.add(num);
        }

        //balance
        if(maxheap.size() > minheap.size() + 1){
            minheap.add(maxheap.poll());
        }else if (minheap.size() > maxheap.size()){
            maxheap.add(minheap.poll());
        }
    }

    private static double findMedian(){
        if(maxheap.size() > minheap.size()){
            return maxheap.peek();
        }else {
            return (maxheap.peek() + minheap.peek())/2.0;
        }
    }
}
