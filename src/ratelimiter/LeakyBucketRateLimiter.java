package google.ratelimiter;

import java.util.*;

/*
In leaky bucket, instead of accumulating tokens, we accumulate requests and drain them at a fixed rate,
ensuring a constant output flow.
 */

public class LeakyBucketRateLimiter {

    private final int capacity;
    private final double leakRate;
    private double currentSize;
    private long lastLeakTime;

    public LeakyBucketRateLimiter(int capacity, double leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.currentSize = 0;
        this.lastLeakTime = System.nanoTime();
    }

    public boolean allowRequest()
    {
        leak();

        if(currentSize  < capacity){
            currentSize += 1; //enqueue request for processing
            return true;
        }

        return false; //reject as queue is full
    }

    private void leak()
    {
        // lazy/on-demand leak using elapsed time, NOT processing at fixed intervals
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastLeakTime) / 1e9;

        double requestsToProcess = elapsedSeconds * leakRate;
        currentSize = Math.max(0, currentSize - requestsToProcess);

        lastLeakTime = now;
    }
}
