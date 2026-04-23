package intervals;

import java.util.*;

public class MeetingRoom {

    //https://leetcode.com/problems/meeting-rooms/description/

    private static boolean canAttendMeetings(int[][] intervals)
    {
        //input validation
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        //sort input intervals by start time in asc order
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        //check adjacent intervals for any overlap
        for(int i=1; i<intervals.length; i++){
            int currStartTime = intervals[i][0];
            int prevEndTime = intervals[i-1][1];

            if(currStartTime < prevEndTime){
                return false; //overlap found
            }
        }

        return true; //no overlap found
    }
}
