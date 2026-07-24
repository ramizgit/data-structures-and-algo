package sliding_window;

import java.util.*;

public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k)
    {
        int n = nums.length;
        double[] median = new double[n - k + 1];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        Map<Integer, Integer> delayed = new HashMap<>();

        int maxHeapSize = 0;
        int minHeapSize = 0;

        int windowStart = 0;
        int idx = 0;

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {

            //---------------- EXPAND ----------------
            int num = nums[windowEnd];

            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
                maxHeapSize++;
            } else {
                minHeap.offer(num);
                minHeapSize++;
            }

            int[] sizes = balance(maxHeap, minHeap, maxHeapSize, minHeapSize);
            maxHeapSize = sizes[0];
            minHeapSize = sizes[1];

            // Proceed only when the window size reaches k
            if (windowEnd - windowStart + 1 < k) {
                continue;
            }

            //---------------- COMPUTE MEDIAN ----------------
            prune(maxHeap, delayed);
            prune(minHeap, delayed);

            if (k % 2 == 1) {
                median[idx++] = maxHeap.peek();
            } else {
                median[idx++] =
                        ((long) maxHeap.peek() + (long) minHeap.peek()) / 2.0;
            }

            //---------------- SHRINK ----------------
            int out = nums[windowStart++];
            delayed.put(out, delayed.getOrDefault(out, 0) + 1);

            prune(maxHeap, delayed);
            prune(minHeap, delayed);

            if (out <= maxHeap.peek()) {
                maxHeapSize--;
            } else {
                minHeapSize--;
            }

            sizes = balance(maxHeap, minHeap, maxHeapSize, minHeapSize);
            maxHeapSize = sizes[0];
            minHeapSize = sizes[1];
        }

        return median;
    }

    private int[] balance(PriorityQueue<Integer> maxHeap,
                          PriorityQueue<Integer> minHeap,
                          int maxHeapSize,
                          int minHeapSize)
    {
        if (maxHeapSize - minHeapSize > 1) {
            minHeap.offer(maxHeap.poll());
            maxHeapSize--;
            minHeapSize++;
        } else if (maxHeapSize < minHeapSize) {
            maxHeap.offer(minHeap.poll());
            maxHeapSize++;
            minHeapSize--;
        }

        return new int[]{maxHeapSize, minHeapSize};
    }

    // Remove elements marked for deletion from heap top
    private void prune(PriorityQueue<Integer> heap, Map<Integer, Integer> delayed) {
        while (!heap.isEmpty()) {
            int num = heap.peek();

            if (!delayed.containsKey(num)) {
                break;
            }

            heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if(delayed.get(num) == 0){
                delayed.remove(num);
            }
        }
    }
}
