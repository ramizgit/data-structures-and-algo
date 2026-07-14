package lineSweep;

/*
Peak Memory Usage Intervals

You are given a list of processes running on a server.

Each process is represented as:

[startTime, endTime, memory]

meaning the process consumes memory MB during the half-open interval:

[startTime, endTime)

Return all maximal intervals during which the total memory usage is equal to the maximum memory usage over the entire schedule.

Method Signature
List<int[]> findPeakMemoryIntervals(int[][] processes)

Each interval should be returned as

[startTime, endTime)
Example

Input

[
    [1,5,4],
    [2,6,3],
    [4,8,5]
]

Timeline

[1,2) -> 4

[2,4) -> 7

[4,5) -> 12   ← maximum

[5,6) -> 8

[6,8) -> 5

Output

[
    [4,5)
]
Example 2
[
    [1,4,5],
    [2,5,5]
]

Timeline

[1,2) -> 5

[2,4) -> 10   ← maximum

[4,5) -> 5

Output

[
    [2,4)
]
Constraints
1 <= processes.length <= 100000

0 <= start < end <= 10^9

1 <= memory <= 10^6
 */

import java.util.*;

public class PeakMemoryUsageIntervals {

    List<int[]> findPeakMemoryIntervals(int[][] processes)
    {
        TreeMap<Integer, Long> events = new TreeMap<>(); //diff. map of {time -> memory usage}

        for(int[] process : processes){

            int start = process[0];
            int end = process[1];
            int memory = process[2];

            events.put(start, events.getOrDefault(start, 0L) + memory); //start event - increment memory
            events.put(end, events.getOrDefault(end, 0L) - memory); //end event - decrement memory
        }

        //first sweep to find max memory usge
        long currMemory = 0L;
        long maxMemory = 0L;

        for(long delta : events.values()){
            currMemory += delta;
            maxMemory = Math.max(maxMemory, currMemory);
        }

        //second sweep to collect all intervals with max memory
        currMemory = 0L; //reset
        int prevTime = -1;
        List<int[]> result = new ArrayList<>();

        for(Map.Entry<Integer, Long> event : events.entrySet()){

            int currTime = event.getKey();

            //remember we are evaluating the interval [prevTime, currTime)

            if(prevTime != -1 && currMemory == maxMemory){
                if (!result.isEmpty() && result.getLast()[1] == prevTime) {
                    //merge if the previous interval ends exactly where the current interval begins.
                    result.getLast()[1] = currTime; //extend last result end time
                } else {
                    result.add(new int[]{prevTime, currTime});
                }
            }

            currMemory += event.getValue();
            prevTime = currTime;
        }

        return result;
    }
}

/*
TEMPLATE:-

build events

↓

(optional) first sweep to compute threshold

↓

second sweep

    inspect [prevTime, currTime)

    merge if adjacent

    apply delta

    prevTime = currTime
 */
