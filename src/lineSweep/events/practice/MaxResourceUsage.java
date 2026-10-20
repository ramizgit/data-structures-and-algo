package lineSweep.events.practice;

import java.util.*;

/*
You are given a 2D integer array jobs, where jobs[i] = [startTime, endTime, resourceUsage] represents a job that consumes resourceUsage units of a shared resource during the half-open interval \([startTime, endTime)\).

Return the maximum total resource usage at any point in time across all running jobs.

Two jobs whose intervals only touch at an endpoint do not overlap at that endpoint.
Example 1
Input
jobs = new int[][] {
    {1, 5, 4},
    {2, 6, 3},
    {5, 8, 6},
    {6, 9, 2}
};


Output
9


Explanation: Between times 5 and 6, the first and third jobs are no longer overlapping, but the second and third jobs are running simultaneously, consuming 3 + 6 = 9 units.
Example 2
Input
jobs = new int[][] {
    {1, 3, 5},
    {3, 7, 8},
    {2, 4, 2}
};


Output
10


Explanation: Between times 2 and 3, the first and third jobs overlap, consuming 5 + 2 = 7 units. Between times 3 and 4, the second and third jobs overlap, consuming 8 + 2 = 10 units.
Constraints
- 1 <= jobs.length <= 200000
- 0 <= startTime < endTime <= 10^9
- 1 <= resourceUsage <= 10^6
 */

public class MaxResourceUsage {

    private static final int START = 1;
    private static final int END = -1;

    public long maxResourceUsage(int[][] jobs)
    {
        //input validation
        if(jobs == null || jobs.length == 0){
            return -1;
        }

        //create list of events
        List<Event> events = new ArrayList<>();

        for(int[] job : jobs){
            events.add(new Event(job[0], START, START * job[2]));
            events.add(new Event(job[1], END, END * job[2]));
        }

        //sort events
        events.sort( (a, b) -> {
            if(a.time != b.time){
                return Long.compare(a.time, b.time); //sort asc time order
            }

            return Integer.compare(a.type, b.type); //process end event before start event
        } );

        //sweep events
        long currUsage = 0;
        long maxUsage = 0;

        for(Event event : events){
            currUsage += event.deltaResource;
            maxUsage = Math.max(maxUsage, currUsage);
        }

        return maxUsage;
    }

    static class Event{
        long time;
        int type;
        long deltaResource;

        public Event(long time, int type, long deltaResource) {
            this.time = time;
            this.type = type;
            this.deltaResource = deltaResource;
        }
    }
}

