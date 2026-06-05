package intervals;

import java.util.*;

public class MergeIntervals {

    //https://leetcode.com/problems/merge-intervals/
    
    /*
    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, 
    and return an array of the non-overlapping intervals that cover all the intervals in the input.
     */

    public int[][] merge(int[][] intervals)
    {
        //input validation
        if(intervals == null || intervals.length == 0){
            return new int[0][0];
        }

        //sort input intervals for by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]); //add first interval

        for(int i=1; i<intervals.length; i++){
            int[] current = intervals[i];
            int[] last = result.getLast(); // 🔥 important

            if(current[0] <= last[1]){
                //overlap found as current intervals starts before last one ends, merge them
                last[1] = Math.max(last[1], current[1]);
            }else{
                //no overlap, add new interval
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }
}
