package intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    //https://leetcode.com/problems/insert-interval/description/
    public static void main(String[] args)
    {
        insertInterval(new int[][]{{1,3}, {6,9}}, new int[]{2,5});
        insertInterval(new int[][]{{1,2}, {3,5}, {6,7}, {8,10},{12,16}}, new int[]{4,8});
    }

    private static int[][] insertInterval(int[][] intervals, int[] newInterval)
    {
        List<int[]> result = new ArrayList<>();
        int idx = 0;

        //step1: add all intervals coming before new interval
        while (idx < intervals.length && intervals[idx][1] < newInterval[0]){
            result.add(intervals[idx]);
            idx++;
        }

        //step2: merge intervals if any
        while (idx < intervals.length && intervals[idx][0] <= newInterval[1]){
            newInterval[0] = Math.min(intervals[idx][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[idx][1], newInterval[1]);
            idx++;
        }
        result.add(newInterval);

        //step3: add all intervals coming after new interval
        while (idx < intervals.length){
            result.add(intervals[idx]);
            idx++;
        }

        //return final result
        return result.toArray(new int[result.size()][]);
    }
}

