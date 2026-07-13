package consistenthashing.lineSweep;

import java.util.*;

public class TheSkylineProblem {

    //https://leetcode.com/problems/the-skyline-problem/description/

    /*
    Create events
    ↓
    Sort events
    ↓
    Sweep from left to right
    ↓
    Maintain active building heights in a max heap
    ↓
    If tallest height changes,
        record a skyline point
     */

    private static final int START = 1;
    private static final int END = 0;

    // Time : O(n²) because PriorityQueue.remove(Object) is O(n).
    // Space: O(n)
    // Note: Can be optimized to O(n log n) using TreeMap (multiset)
    // or a max heap with lazy deletion.
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
            /*
            Insert taller buildings first.
            A shorter building starting underneath a taller one doesn't change the skyline.
            Processing the taller building first avoids creating temporary, incorrect skyline points.
             */
            if (a.type == START && b.type == START) {
                return Integer.compare(b.height, a.height); //taller first - height desc
            }

            // END vs END
            /*
            Remove shorter buildings first.
            Removing a shorter building doesn't affect the visible skyline because a taller building is still covering it.
            Removing the tallest building too early creates a temporary, artificial skyline.
             */
            if (a.type == END && b.type == END) {
                return Integer.compare(a.height, b.height); //smaller first - height asc
            }

            // START before END
            /*
            Buildings starting at x are already visible at that position, while buildings ending at x
            remain visible up to that position. Therefore, process START events before END events
            so the skyline reflects the correct height at x.
             */
            return Integer.compare(b.type, a.type); //At the same x-coordinate, buildings that start already exist, and buildings that end are still visible up to that x. Therefore, process START events before END events.
        } );

        //line sweep
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a, b) -> Integer.compare(b, a )); //always process the tallest active building right now
        int previousMax = 0;
        List<List<Integer>> answer = new ArrayList<>();

        maxHeap.offer(0); //0 represents the ground level, helps avoid empty heap check

        for(Event event : events){

            //update the active buildings.
            if(event.type == START){
                maxHeap.offer(event.height); // building starts
            }else{
                maxHeap.remove(event.height); // building ends
            }

            //find the tallest active building.
            int currentMax = maxHeap.peek();

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
