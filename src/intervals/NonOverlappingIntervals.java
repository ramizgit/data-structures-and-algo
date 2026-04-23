package intervals;

import java.util.*;

public class NonOverlappingIntervals {

    //https://leetcode.com/problems/non-overlapping-intervals/description/

    //Time : O(nlogn)
    public int eraseOverlapIntervals(int[][] intervals)
    {
        if(intervals == null || intervals.length == 0){
            return 0;
        }

        //Greedy approach : always keep the interval that ends earliest, it leaves maximum space for future intervals
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); //sort by end time

        int remove = 0;
        int prevEnd = intervals[0][1];

        for(int i=1; i<intervals.length; i++){
            int[] curr = intervals[i];

            if(curr[0] < prevEnd){
                remove++; //overlap found → remove current interval (greedy choice)
            }else{
                prevEnd = curr[1]; //move forward with current valid interval
            }
        }

        return remove;
    }
}
