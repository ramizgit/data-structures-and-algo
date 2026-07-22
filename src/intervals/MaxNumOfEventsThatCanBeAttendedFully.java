package intervals;

import java.util.Arrays;

public class MaxNumOfEventsThatCanBeAttendedFully {

    /*
    Follow-up:
    You are given an array of events where each event is represented as
    [startDay, endDay].

    Unlike the original problem, if you choose to attend an event, you must
    attend the entire interval from startDay to endDay. Therefore, you cannot
    attend two events whose intervals overlap.

    Return the maximum number of events you can attend.

    Example:
    Input:
    [[1,4], [2,3], [3,5], [6,8]]

    Output:
    2

    Explanation:
    One optimal selection is [2,3] and [6,8].
     */

    //important point : you need to attend event from start to end in full

    //similar approach as : NonOverlappingIntervals

    public int maxEvents(int[][] events)
    {
        //input validation
        if(events == null || events.length == 0){
            return 0;
        }

        //sort by end day
        Arrays.sort(events, (a, b) -> Integer.compare(a[1], b[1]));

        int attended = 0;
        int lastEndTime = Integer.MIN_VALUE;

        for(int[] event : events){

            int currStartTime = event[0];
            int currEndTime = event[1];

            if(currStartTime >= lastEndTime){
                attended++;
                lastEndTime = currEndTime;
            }
        }

        return attended;
    }
}
