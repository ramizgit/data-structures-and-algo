package intervals;

import java.util.*;

public class NonOverlappingIntervals {

    //https://leetcode.com/problems/non-overlapping-intervals/description/

    /*
    Given an array of intervals intervals where intervals[i] = [starti, endi], 
    return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
    Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
     */
    //Time : O(nlogn)
    public int eraseOverlapIntervals(int[][] intervals)
    {
        if(intervals == null || intervals.length == 0){
            return 0;
        }

        //Greedy approach : always keep the interval that ends earliest, it leaves maximum space for future intervals
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); //sort by end time

        int remove = 0;
        int prevEndTime = intervals[0][1];

        for(int i=1; i<intervals.length; i++){
            int currStartTime = intervals[i][0];
            int currentEndTime = intervals[i][1];

            if(currStartTime < prevEndTime){
                remove++; //overlap found → remove current interval (greedy choice)
            }else{
                prevEndTime = currentEndTime; //move forward with current valid interval
            }
        }

        return remove;
    }
}
