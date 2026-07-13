package consistenthashing.lineSweep;

import java.util.TreeMap;

public class MyCalendarii {

    //https://leetcode.com/problems/my-calendar-ii/description/

    /*
    "In My Calendar I, I can determine validity by checking only the predecessor and successor intervals, so I validate before inserting.
    In My Calendar II, whether a booking is valid depends on the global maximum overlap after the new booking is added.
    The easiest way to evaluate that is to temporarily insert its start and end events into the difference map, perform the sweep,
    and roll back if the active booking count reaches 3."
     */

    /*
    Approach: Line Sweep + TreeMap

    Store +1 at booking start and -1 at booking end in a TreeMap.
    For each booking, temporarily add its events and sweep the map in sorted order.
    The prefix sum gives the number of active bookings.
    If it ever reaches 3, rollback the changes and return false; otherwise keep the booking.

    Time: O(n) per booking
    Space: O(n)
    */

    private static final int START_DELTA = 1;
    private static final int END_DELTA = -1;

    // Difference map : +1 at booking start and -1 at booking end
    TreeMap<Integer, Integer> events; //TreeMap<time, delta>

    public MyCalendarii() {
        this.events = new TreeMap<>();
    }

    //time : O(n)
    public boolean book(int startTime, int endTime)
    {
        this.events.put(startTime, this.events.getOrDefault(startTime, 0) + START_DELTA); //O(log n)
        this.events.put(endTime, this.events.getOrDefault(endTime, 0) + END_DELTA); //O(log n)

        int currActive = 0;

        //sweep events
        for(int delta : events.values()){ //O(n)

            currActive += delta;

            if(currActive >= 3){

                //rollback logic
                events.put(startTime, events.get(startTime) - START_DELTA); //rollback start

                if (events.get(startTime) == 0) {
                    events.remove(startTime);
                }

                events.put(endTime, events.get(endTime) - END_DELTA); //rollback end

                if (events.get(endTime) == 0) {
                    events.remove(endTime);
                }

                return false;
            }
        }

        return true;
    }
}
