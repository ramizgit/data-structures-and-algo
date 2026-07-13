package lineSweep;

import java.util.*;

public class MeetingRoomsII {

    //https://leetcode.com/problems/meeting-rooms-ii/description/

    //IMPORTANT : this can be solved via both heap and line sweep. for heap approach, look inside intervals package

    private static final int START_DELTA = 1;
    private static final int END_DELTA = -1;

    // Time  : O(n log n)
    // Space : O(n)
    public int minMeetingRooms(int[][] intervals)
    {
        //input validation
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        //create events list
        List<Event> events = new ArrayList<>();

        for(int[] interval : intervals){ //O(n)
            events.add(new Event(interval[0], START_DELTA)); //start
            events.add(new Event(interval[1], END_DELTA)); //end
        }

        //sort events by time asc order. If two events occur at the same time, process END (-1) before START (+1).
        events.sort( (a, b) -> {

            if(a.time != b.time){
                return Integer.compare(a.time, b.time);
            }

            return Integer.compare(a.delta, b.delta); // END (-1) before START (+1)
        } ); // Time: O(n log n)

        int activeMeetings = 0;
        int maxConcurrentMeetings = 0;

        //sweep events
        for(Event event : events){ //O(n)
            activeMeetings += event.delta;
            maxConcurrentMeetings = Math.max(maxConcurrentMeetings, activeMeetings);
        }

        return maxConcurrentMeetings; //max concurrent meetings at any point in time (max overlap) is the min number of rooms required
    }

    static class Event{
        int time;
        int delta; //+1 for start, -1 for end

        public Event(int time, int delta) {
            this.time = time;
            this.delta = delta;
        }
    }
}
