package intervals;

import java.util.*;

public class MeetingRoomII {

    /*
    Example:
    [ [0,10], [1,5], [2,7], [6,9] ]
    Answer = 3

    Key idea:
    We need to find the MAX number of meetings running at the same time.
    That equals the number of rooms required.
    */

    public int minMeetingRooms(int[][] intervals) {

        // Edge case: no meetings → no rooms needed
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Step 1: Sort meetings by start time
        // So we process meetings in chronological order
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        /*
        Step 2: Min Heap (PriorityQueue)

        What does heap store?
        → End times of ALL currently ongoing meetings

        Why min heap?
        → We always want the EARLIEST ending meeting
        → That tells us which room frees up first
        */
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add the first meeting's end time → allocate first room
        minHeap.offer(intervals[0][1]);

        /*
        Step 3: Process remaining meetings
        */
        for (int i = 1; i < intervals.length; i++) {

            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            /*
            Check if the earliest ending meeting is finished

            minHeap.peek() → earliest end time among all active meetings

            If current meeting starts AFTER or AT that time:
            → that room is now free → reuse it
            */
            if (currStart >= minHeap.peek()) {
                minHeap.poll(); // free that room
            }

            /*
            Allocate room for current meeting
            (either reused or new)
            */
            minHeap.offer(currEnd);
        }

        /*
        Final heap size = number of rooms needed

        Because heap contains ALL meetings that are overlapping
        at peak time
        */
        return minHeap.size();
    }
}
