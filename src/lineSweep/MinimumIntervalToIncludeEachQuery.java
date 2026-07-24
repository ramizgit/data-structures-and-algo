package lineSweep;

import java.util.*;

public class MinimumIntervalToIncludeEachQuery {

    //https://leetcode.com/problems/minimum-interval-to-include-each-query/description/

    /*
    Sort intervals by start
    ↓
    Store queries as
    (value, originalIndex)
    ↓
    Sweep queries
    ↓
    Add newly eligible intervals
    ↓
    Remove expired intervals
    ↓
    Top of heap = answer
    ↓
    Write answer into original index
    */

    // Time : O((n + q) log n)
    // Space: O(n + q)
    public int[] minInterval(int[][] intervals, int[] queries)
    {
        //create interval list along with interval size, precompute interval lengths for efficient heap ordering
        List<Interval> intervalList = new ArrayList<>();

        for(int[] interval : intervals){
            int start = interval[0];
            int end = interval[1];

            intervalList.add(new Interval(start, end));
        }

        //sort interval by start
        intervalList.sort( (a, b) -> Integer.compare(a.start, b.start) );

        //store queries along with index, so that we dont lose ordering while sorting
        List<int[]> queriesWithIdx = new ArrayList<>();
        for(int i=0; i<queries.length; i++){
            queriesWithIdx.add(new int[]{queries[i], i});
        }

        //sort queries in asc order
        queriesWithIdx.sort( (a, b) -> Integer.compare(a[0], b[0]));

        // Sweep queries from left to right while maintaining all active intervals in a min-heap ordered by interval length.
        PriorityQueue<Interval> minHeap = new PriorityQueue<>( (a, b) -> Integer.compare(a.size, b.size));

        int[] result = new int[queries.length];
        int intervalIndex = 0;

        for(int[] query : queriesWithIdx){

            int queryValue = query[0];
            int queryIndex = query[1];

            //add all intervals whose start <= query
            while(intervalIndex < intervalList.size() && intervalList.get(intervalIndex).start <= queryValue){
                minHeap.offer(intervalList.get(intervalIndex));
                intervalIndex++;
            }

            //remove all expired intervals whose end < query
            while(!minHeap.isEmpty() && minHeap.peek().end < queryValue){
                minHeap.poll();
            }

            /*
            The lazy-deletion idea makes step 2 efficient:
            Expired intervals that are not at the top can stay in the heap.
            They don't affect the answer because the heap is ordered by interval size.
            When an expired interval eventually reaches the top, it gets removed.
             */

            //heap top is the smallest interval that contains the current query.
            if(!minHeap.isEmpty()){
                //Heap top is the smallest valid interval
                Interval minSizeInterval = minHeap.peek(); //do not remove heap top here, as same interval can be answer for other queries
                result[queryIndex] = minSizeInterval.size;
            }else{
                result[queryIndex] = -1;
            }
        }

        return result;
    }

    static class Interval{
        int start;
        int end;
        int size;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            this.size = end - start + 1;
        }
    }
}
