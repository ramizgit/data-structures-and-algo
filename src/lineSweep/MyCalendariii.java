package consistenthashing.lineSweep;

import java.util.*;

public class MyCalendariii {

    //https://leetcode.com/problems/my-calendar-iii/description/

    /*
    Notice how this is almost identical to Car Pooling.

    Car Pooling:
        diff[start] += passengers
        diff[end]   -= passengers
        ↓
        Prefix Sum

    My Calendar III:
        TreeMap[start] += 1
        TreeMap[end]   -= 1
        ↓
        Prefix Sum over TreeMap

    The only difference is:

    Car Pooling: coordinates are bounded (0..1000), so use an array.
    My Calendar III: coordinates can be up to 10^9, so use a TreeMap.

    That's why I like to think of My Calendar III as "Difference Array on Sparse Coordinates."
    It's the same sweep-line idea with a different data structure. This is a great pattern to remember.
     */

    // Difference map : +1 at booking start and -1 at booking end
    TreeMap<Integer, Integer> events;

    public MyCalendariii() {
        this.events = new TreeMap<>();
    }

    public int book(int startTime, int endTime)
    {
        this.events.put(startTime, this.events.getOrDefault(startTime, 0) + 1); //O(log n)
        this.events.put(endTime, this.events.getOrDefault(endTime, 0) - 1); //O(log n)

        int active = 0;
        int maxActive = 0;

        for(int delta : events.values()){ //O(n)
            active += delta;
            maxActive = Math.max(maxActive, active);
        }

        return maxActive;
    }
}
