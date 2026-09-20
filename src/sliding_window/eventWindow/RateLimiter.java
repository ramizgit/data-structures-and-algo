package sliding_window.eventWindow;

import java.util.*;

/*
Rate Limiter

You are designing a per-user API rate limiter.

Each user is allowed to make at most K requests within any rolling W-second window.

A request is represented by:

(userId, timestamp)

Implement the following class:

class RateLimiter {

    public RateLimiter(int k, long window) {
        // initialize
    }

    public boolean allow(String userId, long timestamp) {
        // implement
    }
}
Rules
Each user can make at most K allowed requests within the previous W seconds.
Requests arrive in non-decreasing timestamp order.
If a request is rejected, it does not count toward the limit.
A request at exactly W seconds after an earlier request is allowed.
Different users have independent rate limits.
Example
RateLimiter limiter = new RateLimiter(3, 10);

limiter.allow("A", 1)  → true
limiter.allow("A", 2)  → true
limiter.allow("A", 5)  → true
limiter.allow("A", 7)  → false
limiter.allow("A", 11) → true
limiter.allow("A", 12) → false

For the request at t = 11, the request at t = 1 has expired because:

11 - 1 = 10

so only requests at 2 and 5 remain in the window.

Goal: Implement allow() with O(1) amortized time per request.
 */

public class RateLimiter {

    private Map<String, Queue<Long>> userToEventQueue;
    int k;
    long window;

    public RateLimiter(int k, long window) {
        this.userToEventQueue = new HashMap<>();
        this.k = k;
        this.window = window;
    }

    public boolean allow(String userId, long timestamp) {

        //first expire old events (if any)
        Queue<Long> eventsQueue = this.userToEventQueue.computeIfAbsent(userId, key -> new ArrayDeque<>());

        while(!eventsQueue.isEmpty() && eventsQueue.peek() <= timestamp - window){
            eventsQueue.poll();
        }

        if(eventsQueue.size() >= k){
            return false;
        }

        eventsQueue.offer(timestamp);

        return true;
    }
}
