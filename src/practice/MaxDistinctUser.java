package practice;

import java.util.*;

/*
Problem 1: Maximum Events in a Time Window
Medium–Hard
 Sliding Window + Data Structures


You are given an array of events. Each event is represented as:

[timestamp, userId]

The events are sorted by timestamp in non-decreasing order.

You are also given an integer W, representing a time window.

At any timestamp t, consider all events whose timestamps fall within the interval:
\[
(t-W,\ t]
\]

Your task is to return the maximum number of distinct users active in any such time window.

Important: A user can generate multiple events within the same window, but should be counted only once.
Example 1
events = [
    [1, "A"],
    [2, "B"],
    [3, "A"],
    [5, "C"],
    [6, "B"],
    [8, "D"]
]

W = 4


At timestamp 6, the active interval is (2, 6].

Events inside this interval:
[3, "A"]
[5, "C"]
[6, "B"]


Distinct users = {A, B, C}.
 */

public class MaxDistinctUser {

    //input is guaranteed to be sorted by timestamp.
    public int maxDistinctUsers(int[][] events, int W)
    {
        //hint : deque + frequency map sliding window

        Deque<Event> eventsQueue = new ArrayDeque<>();
        Map<Integer, Integer> freqMap = new HashMap<>(); //{userId : freq}

        int maxDistinctUser = 0;

        for(int[] event : events){

            int currTimestamp = event[0];
            int currUserId = event[1];

            //remove expired events
            while(!eventsQueue.isEmpty() && eventsQueue.peekFirst().timestamp <= currTimestamp - W){
                Event expiredEvent = eventsQueue.pollFirst();

                //update freq map
                freqMap.put(expiredEvent.userId, freqMap.get(expiredEvent.userId) - 1);
                if(freqMap.get(expiredEvent.userId) == 0){
                    freqMap.remove(expiredEvent.userId);
                }
            }

            //put curr event at the last
            eventsQueue.offerLast(new Event(currTimestamp, currUserId));

            //update freq map
            freqMap.put(currUserId, freqMap.getOrDefault(currUserId, 0) + 1);

            maxDistinctUser = Math.max(maxDistinctUser, freqMap.size());
        }

        return maxDistinctUser;
    }

    static class Event{
        int timestamp;
        int userId;

        public Event(int timestamp, int userId) {
            this.timestamp = timestamp;
            this.userId = userId;
        }
    }
}
