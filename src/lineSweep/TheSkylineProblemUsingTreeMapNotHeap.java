package consistenthashing.lineSweep;

import java.util.*;

public class TheSkylineProblemUsingTreeMapNotHeap {

    //https://leetcode.com/problems/the-skyline-problem/description/

    /*
    Create events
    ↓
    Sort events
    ↓
    Sweep from left to right
    ↓
    Maintain active building heights in a TreeMap
    ↓
    If tallest height changes,
        record a skyline point
     */

    private static final int START = 1;
    private static final int END = 0;

    // Time : O(n log n)
    // Space: O(n)
    public List<List<Integer>> getSkyline(int[][] buildings)
    {
        //input validation
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }

        //create events as list
        List<Event> events = new ArrayList<>();

        for(int[] building : buildings){

            int start = building[0];
            int end = building[1];
            int height = building[2];

            events.add(new Event(start, height, START));
            events.add(new Event(end, height, END));
        }

        //sort events
        /*
        At the same x:
        START before END
        START vs START: taller first - after adding taller one, smaller ones dont change event which is expected output
        END vs END: shorter first - removing smaller ones first dont change the event which is expected output
         */
        events.sort( (a, b) -> {

            if(a.x != b.x){
                return Integer.compare(a.x, b.x); //x asc if no overlap
            }

            // START vs START
            if (a.type == START && b.type == START) {
                return Integer.compare(b.height, a.height); //taller first - height desc
            }

            // END vs END
            if (a.type == END && b.type == END) {
                return Integer.compare(a.height, b.height); //smaller first - height asc
            }

            // START before END
            return Integer.compare(b.type, a.type); //At the same x-coordinate, buildings that start already exist, and buildings that end are still visible up to that x. Therefore, process START events before END events.
        } );

        //line sweep
        TreeMap<Integer, Integer> heights = new TreeMap<>(); //The key is the height, and the value is the frequency (how many active buildings have that height).
        int previousMax = 0;
        List<List<Integer>> answer = new ArrayList<>();

        heights.put(0, 1); //0 represents the ground level, helps avoid empty heap check

        for(Event event : events){

            //update the active buildings.
            if(event.type == START){
                //maxHeap.offer(event.height); // building starts
                heights.put(event.height, heights.getOrDefault(event.height, 0) + 1); // building starts
            }else{
                // building ends
                heights.put(event.height, heights.get(event.height) - 1);
                if(heights.get(event.height) == 0){
                    heights.remove(event.height);
                }
            }

            //find the tallest active building.
            int currentMax = heights.lastKey();

            //compare with the previous tallest, if it changed, record a skyline point.
            if (currentMax != previousMax) {
                answer.add(Arrays.asList(event.x, currentMax));
                previousMax = currentMax;
            }
        }

        return answer;
    }

    static class Event{
       int x;
       int height;
       int type; //1 for start and 0 for end

        public Event(int x, int height, int type) {
            this.x = x;
            this.height = height;
            this.type = type;
        }
    }
}
