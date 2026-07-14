package lineSweep;

/*
Total Passenger Threshold Intervals

You are given a list of flights, where each flight is represented as:

[startTime, endTime, passengers]

This means passengers are on board during the half-open interval:

[startTime, endTime)

Given an integer P, return all maximal intervals during which the total number of passengers across all active flights is greater than or equal to P.

An interval is maximal if it cannot be extended to the left or right while still satisfying the condition.

If no such interval exists, return an empty list.

Method Signature
List<int[]> findBusyIntervals(int[][] flights, long P)

Each returned interval should be represented as:

[startTime, endTime)
Example 1
Input:

flights =
[
    [1,5,10],
    [3,7,5],
    [6,9,10]
]

P = 15

Output:

[
    [3,5],
    [6,7]
]

Explanation:

[1,3) -> 10

[3,5) -> 15 ✓

[5,6) -> 5

[6,7) -> 15 ✓

[7,9) -> 10
Example 2
Input:

flights =
[
    [1,10,5],
    [3,7,10]
]

P = 10

Output:

[
    [3,7]
]
Constraints
1 <= flights.length <= 100,000

0 <= startTime < endTime <= 10^9

1 <= passengers <= 10^6

1 <= P <= 10^11
Notes
Intervals are half-open: [startTime, endTime).
Multiple flights may start or end at the same time.
Return maximal intervals only.
The returned intervals should be sorted by start time.
If no interval satisfies the condition, return an empty list.
 */

import java.util.*;

public class FindBusyIntervals {

    //todo : practice

    List<int[]> findBusyIntervals(int[][] flights, long P) {
        //create list of events
        TreeMap<Integer, Integer> events = new TreeMap<>(); //diff. map of {time -> passenger count}

        for (int[] flight : flights) {

            int start = flight[0];
            int end = flight[1];
            int passengers = flight[2];

            events.put(start, events.getOrDefault(start, 0) + passengers);
            events.put(end, events.getOrDefault(end, 0) - passengers);
        }

        //sweep
        List<int[]> result = new ArrayList<>();
        long activePassengers = 0;
        Integer prevTime = null;

        for (Map.Entry<Integer, Integer> entry : events.entrySet()) {

            /*
            active always describes the interval from prevTime up to the current event time.
            After processing the current event, it becomes the passenger count for the next interval.
            Once you internalize that, the ordering in the sweep becomes very natural.
             */

            int currTime = entry.getKey();

            //remember we are evaluating the interval [prevTime, currTime)

            //interval [prevTime, currTime) has the previous active passenger count
            if (prevTime != null && activePassengers >= P) {

                // Merge with previous interval if adjacent
                if (!result.isEmpty() && result.getLast()[1] == prevTime) {
                    //does this new interval start where the previous one ended?
                    result.getLast()[1] = currTime; //extend last result end time
                } else {
                    result.add(new int[]{prevTime, currTime});
                }
            }

            //apply all passenger changes at currTime
            activePassengers += entry.getValue(); //important : this active count is valid from curr time till nex interval time

            prevTime = currTime;
        }

        return result;
    }
}
