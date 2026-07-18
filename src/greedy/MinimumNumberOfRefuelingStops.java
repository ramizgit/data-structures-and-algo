package greedy;

import java.util.*;

public class MinimumNumberOfRefuelingStops {

    // https://leetcode.com/problems/minimum-number-of-refueling-stops/

    //todo : practice

    public int minRefuelStops(int target, int startFuel, int[][] stations) {

        /*
        Greedy Idea:

        Stations are processed in increasing order of position.

        1. Keep all reachable stations in a max heap.
        2. Only refuel when the next station (or target) is unreachable.
        3. When refueling, always choose the station with the maximum fuel.
        */

        int answer = 0;

        PriorityQueue<Integer> fuelMaxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        int capacity = startFuel; // Maximum distance we can currently reach

        for (int i = 0; i < stations.length; i++) {

            int position = stations[i][0];
            int fuel = stations[i][1];

            //can't reach this station yet, keep refueling until we can or run out of options.
            while (capacity < position) {

                if (fuelMaxHeap.isEmpty()) {
                    return -1;
                }

                capacity += fuelMaxHeap.poll();
                answer++;
            }

            // This station is now reachable.
            fuelMaxHeap.offer(fuel);
        }

        // After all stations, keep refueling until we reach the target.
        while (capacity < target) {

            if (fuelMaxHeap.isEmpty()) {
                return -1;
            }

            capacity += fuelMaxHeap.poll();
            answer++;
        }

        return answer;
    }
}