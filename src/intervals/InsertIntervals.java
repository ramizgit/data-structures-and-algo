package intervals;

import java.util.*;

public class InsertIntervals {

    //https://leetcode.com/problems/insert-interval/

    public int[][] insert(int[][] intervals, int[] newInterval)
    {
        List<int[]> result = new ArrayList<>();

        int idx = 0;

        //step1 : add intervals ending before the new interval start time
        while(idx < intervals.length && intervals[idx][1] < newInterval[0]){
            result.add(intervals[idx]);
            idx++;
        }

        //step2 : merge overlapping intervals if any
        //stretch the new interval to absorb all overlaps, then insert it once.
        while(idx < intervals.length && intervals[idx][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
            idx++;
        }
        result.add(newInterval);

        //step3 : add rest of the intervals coming after the new interval
        while(idx < intervals.length){
            result.add(intervals[idx]);
            idx++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
