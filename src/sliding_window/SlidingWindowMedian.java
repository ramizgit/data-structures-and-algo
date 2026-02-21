package slidingWindow;

import java.util.*;

public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k)
    {
        int n = nums.length;
        double[] median = new double[n-k+1];
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        PriorityQueue<Integer> maxheap = new PriorityQueue<>( (a,b) -> b - a );
        Map<Integer, Integer> tobeDeleted = new HashMap<>();
        int maxheapSize = 0;
        int minheapSize = 0;
        int idx = 0;

        for(int i=0; i<nums.length; i++){
            int num = nums[i];
            //-------------ADD TO HEAPS
            if(maxheap.isEmpty() || num <= maxheap.peek()){
                maxheap.offer(num);
                maxheapSize++;
            }else{
                minheap.offer(num);
                minheapSize++;
            }

            //---------BALANE
            if(maxheapSize - minheapSize > 1){
                minheap.offer(maxheap.poll());
                maxheapSize--;
                minheapSize++;
            }else if(maxheapSize < minheapSize){
                maxheap.offer(minheap.poll());
                maxheapSize++;
                minheapSize--;
            }

            // ---------- COMPUTE MEDIAN ----------
            if (i >= k - 1) {

                prune(maxheap, tobeDeleted);
                prune(minheap, tobeDeleted);

                if (k % 2 == 1) {
                    median[idx++] = maxheap.peek();
                } else {
                    median[idx++] = ((long) maxheap.peek() + (long) minheap.peek()) / 2.0;
                }
            }

            //-------REMOVE WHEN LEFT SHRINKS, BUT
            if(i >= k){
                int out = nums[i-k];
                tobeDeleted.put(out, tobeDeleted.getOrDefault(out, 0) + 1);

                // Ensure tops are valid BEFORE comparison
                prune(maxheap, tobeDeleted);
                prune(minheap, tobeDeleted);

                if(out <= maxheap.peek()){
                    maxheapSize--;
                }else{
                    minheapSize--;
                }

                //---------BALANE AGAIN AFTER REMOVAL
                if(maxheapSize - minheapSize > 1){
                    minheap.offer(maxheap.poll());
                    maxheapSize--;
                    minheapSize++;
                }else if(maxheapSize < minheapSize){
                    maxheap.offer(minheap.poll());
                    maxheapSize++;
                    minheapSize--;
                }
            }
        }

        return median;
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
