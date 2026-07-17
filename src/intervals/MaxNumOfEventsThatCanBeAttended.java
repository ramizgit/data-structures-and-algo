package intervals;

import java.util.*;

public class MaxNumOfEventsThatCanBeAttended {

    //https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/

    /*
    Greedy Idea:

    1. Process the calendar one day at a time.
    2. Keep all currently available events in a min-heap ordered by end day.
    3. Each day, attend the event that finishes the earliest.
    4. This minimizes the chance of missing events that expire sooner.
    */

    public int maxEvents(int[][] events)
    {
        //input validation
        if(events == null || events.length == 0){
            return 0;
        }

        //sort events asc order of start days
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        //minheap by end days
        PriorityQueue<int[]> minheap = new PriorityQueue<>( (a, b) -> Integer.compare(a[1], b[1]) );

        int day = events[0][0];
        int idx = 0;
        int answer = 0;

        while(idx < events.length || !minheap.isEmpty()){

            //if there are no active events, jump directly to the next event's start day.
            //this avoids iterating through days with no events.
            if(minheap.isEmpty()){
                day = events[idx][0];
            }

            //add all events that become available today.
            while(idx < events.length && events[idx][0] == day){
                minheap.offer(events[idx]);
                idx++;
            }

            //remove expired events - which have ended before today
            while(!minheap.isEmpty() && minheap.peek()[1] < day){
                minheap.poll();
            }

            if(!minheap.isEmpty()){
                //attend event
                answer++;

                //once you attend an event, it is finished, remove it from heap
                minheap.poll();
            }

            day++; //move to the next day.
        }

        return answer;
    }
}
