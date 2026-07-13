package lineSweep;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendar {

    //https://leetcode.com/problems/my-calendar-i/

    /*
    Chose a TreeMap because bookings are inserted online, and for a new interval, only the immediate predecessor and successor can overlap with it
    if the existing bookings are already non-overlapping. TreeMap lets me find those neighbors in O(log n) using floorEntry and ceilingEntry.
    A list would require O(n) insertion or scanning, and a difference array or line sweep is better suited to counting overlaps across the entire timeline,
    which is unnecessary for My Calendar I.
     */

    private TreeMap<Integer, Integer> bookings;

    public MyCalendar() {
        bookings = new TreeMap<>();
    }

    //time : O(log n)
    public boolean book(int start, int end)
    {
        //check intervals immediately before and after the new interval

        //check floor for immediately before interval
        Map.Entry<Integer, Integer> floorEntry = bookings.floorEntry(start);
        if( floorEntry != null && start < floorEntry.getValue() ){
            return false; //overlap found
        }

        //check ceiling for immediately after interval
        Map.Entry<Integer, Integer> ceilingEntry = bookings.ceilingEntry(start);
        if( ceilingEntry != null && end > ceilingEntry.getKey()){
            return false; //overlap found
        }

        bookings.put(start, end); //populate booking if no overlap

        return true; //no overlap found
    }
}
