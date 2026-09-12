package heap;

import java.util.*;

//todo : practice
public class FindMedianWithDeletionOption {

    private static PriorityQueue<Integer> minheap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
    private static Map<Integer, Integer> deleted = new HashMap<>();

    private static int maxHeapSize = 0;
    private static int minHeapSize = 0;

    private static void add(int num) {

        if (maxheap.isEmpty() || num <= maxheap.peek()) {
            maxheap.add(num);
            maxHeapSize++;
        } else {
            minheap.add(num);
            minHeapSize++;
        }

        balance();
    }

    private static void remove(int num) {

        deleted.put(num, deleted.getOrDefault(num, 0) + 1);

        // We need to know which logical heap the value belongs to.
        clean(maxheap);
        clean(minheap);

        if (!maxheap.isEmpty() && num <= maxheap.peek()) {
            maxHeapSize--;
        } else {
            minHeapSize--;
        }

        balance();
    }

    private static void balance() {

        clean(maxheap);
        clean(minheap);

        if (maxHeapSize > minHeapSize + 1) {
            minheap.add(maxheap.poll());
            maxHeapSize--;
            minHeapSize++;
        } else if (minHeapSize > maxHeapSize) {
            maxheap.add(minheap.poll());
            minHeapSize--;
            maxHeapSize++;
        }
    }

    private static void clean(PriorityQueue<Integer> heap) {

        while (!heap.isEmpty()) {

            int num = heap.peek();
            int count = deleted.getOrDefault(num, 0);

            if (count == 0) {
                break;
            }

            heap.poll();

            if (count == 1) {
                deleted.remove(num);
            } else {
                deleted.put(num, count - 1);
            }
        }
    }

    private static double findMedian() {

        clean(maxheap);
        clean(minheap);

        if (maxHeapSize > minHeapSize) {
            return maxheap.peek();
        } else {
            return (maxheap.peek() + minheap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] stream = {5, 15, 1, 3, 8, 7, 9};

        for (int num : stream) {
            add(num);
            System.out.println("Current Median: " + findMedian());
        }

        remove(5);
        System.out.println("After removing 5: " + findMedian());

        remove(8);
        System.out.println("After removing 8: " + findMedian());
    }
}