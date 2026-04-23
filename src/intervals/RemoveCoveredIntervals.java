package intervals;

import java.util.*;

public class RemoveCoveredIntervals {

    //https://leetcode.com/problems/remove-covered-intervals/description/

    /*
    Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri),
    remove all intervals that are covered by another interval in the list.
    The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
    Return the number of remaining intervals.
     */

    public int removeCoveredIntervals(int[][] intervals)
    {
        if(intervals == null || intervals.length == 0){
            return 0;
        }

        //APPROACH : for coverage problems → sort by start ASC and end DESC

        //sort input intervals so that bigger intervals come first and smaller (covered) ones come later
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        int count = 0;
        int prevMaxEnd = 0;

        for (int[] interval : intervals) {
            if (interval[1] > prevMaxEnd) {
                count++; //not covered
                prevMaxEnd = interval[1];
            }
        }

        return count;
    }
}
