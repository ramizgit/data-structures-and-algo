package sliding_window.eventWindow;

import java.util.*;

public class HitCounter {

    Deque<Integer> events;
    int threshold;

    public HitCounter(int threshold) {
        this.events = new ArrayDeque<>();
        this.threshold = threshold;
    }

    public void hit(int timestamp)
    {
        this.expire(timestamp);
        this.events.offerLast(timestamp);
    }

    public int getHits(int timestamp)
    {
        this.expire(timestamp);
        return this.events.size();
    }

    private void expire(int timestamp)
    {
        while(!this.events.isEmpty() && this.events.peekFirst() <= timestamp - threshold){
            this.events.pollFirst();
        }
    }
}
