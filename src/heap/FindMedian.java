package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedian {

    private PriorityQueue<Integer> minheap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxheap = new PriorityQueue<>( (a, b) -> Integer.compare(b, a));

    private void add(int num)
    {
        if(maxheap.isEmpty() || num <= maxheap.peek()){
            maxheap.add(num); //smaller numbers go into maxheap.
        }else{
            minheap.add(num); //larger numbers go into minheap.
        }

        //balance
        if(maxheap.size() > minheap.size() + 1){
            minheap.add(maxheap.poll());
        }else if (minheap.size() > maxheap.size()){
            maxheap.add(minheap.poll());
        }
    }

    private double findMedian(){
        if(maxheap.size() > minheap.size()){
            return maxheap.peek();
        }else {
            return (maxheap.peek() + minheap.peek())/2.0;
        }
    }
}
