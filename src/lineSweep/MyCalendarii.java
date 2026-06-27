package consistenthashing.lineSweep;

import java.util.TreeMap;

public class MyCalendarii {

    //https://leetcode.com/problems/my-calendar-ii/description/

    private static final int START = 1;
    private static final int END = -1;

    // Difference map : +1 at booking start and -1 at booking end
    TreeMap<Integer, Integer> events;

    public MyCalendarii() {
        this.events = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime)
    {
        this.events.put(startTime, this.events.getOrDefault(startTime, 0) + START); //O(log n)
        this.events.put(endTime, this.events.getOrDefault(endTime, 0) + END); //O(log n)

        int currActive = 0;

        for(int delta : events.values()){ //O(n)
            currActive += delta;

            if(currActive >= 3){

                //rollback logic
                events.put(startTime, events.get(startTime) - START); //rollback start

                if (events.get(startTime) == 0) {
                    events.remove(startTime);
                }

                events.put(endTime, events.get(endTime) - END); //rollback end

                if (events.get(endTime) == 0) {
                    events.remove(endTime);
                }

                return false;
            }
        }

        return true;
    }
}
