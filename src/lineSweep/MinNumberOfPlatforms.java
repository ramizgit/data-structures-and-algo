package consistenthashing.lineSweep;

import java.util.*;

public class MinNumberOfPlatforms {

    /*
    Problem Statement: We are given two arrays that represent the arrival and departure times of trains that stop at the platform.
    We need to find the minimum number of platforms needed at the railway station so that no train has to wait.
     */

    private static final int ARRIVAL = 1;
    private static final int DEPARTURE = -1;

    //Time : O(n + nlog n) ~ O(nlog n)
    //Space : O(n)
    public int minPlatform(int[] arr, int[] dep)
    {
        //input validation

        //single list of events
        List<Event> events = new ArrayList<>();

        //add arrival events
        for(int a : arr){ //O(n)
            events.add(new Event(a, ARRIVAL));
        }

        //add departure events
        for(int d : dep){ //O(n)
            events.add(new Event(d, DEPARTURE));
        }

        //sort by time, if arrival and departure happen at the same time, process departure first so the platform is freed before assigning it to the arriving train.
        events.sort( (a, b) -> {

            if (a.time != b.time) {
                return Integer.compare(a.time, b.time);
            }

            return Integer.compare(a.delta, b.delta); // -1 (departure) before +1 (arrival)
        } ); //O(nlog n)

        int currTrains = 0;
        int maxConcurrentTrains = 0;

        for(Event event : events){ //O(n)
            currTrains += event.delta;
            maxConcurrentTrains = Math.max(maxConcurrentTrains, currTrains);
        }

        return maxConcurrentTrains; //min number of platforms needed to host maxConcurrentTrains without waiting
    }

    static class Event{
        int time;
        int delta; //1 for arrival, -1 for departure

        public Event(int time, int delta) {
            this.time = time;
            this.delta = delta;
        }
    }
}
