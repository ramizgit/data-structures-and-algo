package consistenthashing.intervals;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarI {

    //https://leetcode.com/problems/my-calendar-i/description/

    TreeMap<Integer, Integer> intervals; // {startTime -> endTime} sorted by startTime

    public MyCalendarI() {
        this.intervals = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime)
    {
        //check overlap with the previous interval
        Map.Entry<Integer, Integer> floorEntry = this.intervals.floorEntry(startTime);

        if(floorEntry != null && startTime < floorEntry.getValue()){
            return false; //overlap found as curr entry starts even before the prev entry has ended
        }

        //check overlap with the next interval
        Map.Entry<Integer, Integer> ceilingEntry = this.intervals.ceilingEntry(startTime);

        if(ceilingEntry != null && endTime > ceilingEntry.getKey()){
            return false; //overlap found as next entry starts even before curr entry is finished
        }

        //add the entry if no overlap
        this.intervals.put(startTime, endTime);

        return true;
    }
}
