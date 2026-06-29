package consistenthashing.lineSweep;

import java.util.*;

public class MinimumIntervalToIncludeEachQuery {

    //https://leetcode.com/problems/minimum-interval-to-include-each-query/description/

    /*
    Sort intervals
        ↓
    Sort queries
            ↓
    Sweep queries left to right
            ↓
    Add newly eligible intervals
            ↓
    Remove expired intervals
            ↓
    Top of heap = answer
     */

    public int[] minInterval(int[][] intervals, int[] queries)
    {
        //sort interval by start
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        //store queries along with index, so that we dont lose ordering while sorting
        List<int[]> queriesWithIdx = new ArrayList<>();
        for(int i=0; i<queries.length; i++){
            queriesWithIdx.add(new int[]{queries[i], i});
        }

        //sort queries in asc order
        queriesWithIdx.sort( (a, b) -> Integer.compare(a[0], b[0]));

        // Sweep queries from left to right while maintaining all active intervals in a min-heap ordered by interval length.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>( (a, b) -> {
            int size1 = a[1] - a[0] + 1;
            int size2 = b[1] - b[0] + 1;

            return Integer.compare(size1, size2); //order by interval size asc order
        });

        int[] result = new int[queries.length];
        int lastIntervalIdx = 0;

        for(int[] query : queriesWithIdx){

            //add all intervals whose start <= query
            while(lastIntervalIdx < intervals.length && intervals[lastIntervalIdx][0] <= query[0]){
                minHeap.offer(intervals[lastIntervalIdx]);
                lastIntervalIdx++;
            }

            //remove all intervals whose end < query
            while(!minHeap.isEmpty() && minHeap.peek()[1] < query[0]){
                minHeap.poll();
            }

            /*
            The lazy-deletion idea makes step 2 efficient:
            Expired intervals that are not at the top can stay in the heap.
            They don't affect the answer because the heap is ordered by interval size.
            When an expired interval eventually reaches the top, it gets removed.
             */

            //take min and add to result
            if(!minHeap.isEmpty()){
                //Heap top is the smallest valid interval
                int[] minSizeInterval = minHeap.peek();
                result[query[1]] = minSizeInterval[1] - minSizeInterval[0] + 1;
            }else{
                result[query[1]] = -1;
            }
        }

        return result;
    }
}
