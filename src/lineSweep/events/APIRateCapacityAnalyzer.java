package lineSweep.events;

import java.util.*;

/*
Problem: API Rate-Capacity Analyzer

You're building an API gateway that receives configuration rules for different customers.

Each rule is:

(customerId, startTime, endTime, capacity)

The rule means:

During [startTime, endTime), this customer consumes capacity units of gateway capacity.

Rules can overlap, and multiple rules for the same customer are allowed.

You need to implement:

analyze(rules)

which returns the first timestamp at which the total capacity exceeds MAX_CAPACITY, along with the set of customers responsible for the overload at that exact timestamp.

Example
MAX_CAPACITY = 10

Rule 1: (A, 1, 5, 6)
Rule 2: (B, 2, 7, 4)
Rule 3: (C, 5, 8, 8)

Timeline:

t=1 → A = 6
t=2 → A+B = 10
t=5 → B+C = 12   ← first overload

Result:

timestamp = 5
customers = [B, C]
Important details

At the same timestamp:

A rule ending at t is not active at t.
A rule starting at t is active at t.

So:

[A: 1,5)
[B: 5,8)

At t = 5, only B contributes.

Constraints
Up to 10^6 rules
startTime, endTime up to 10^9
capacity up to 10^6
customerId is a string
Rules are not provided in timestamp order.
You need the earliest overload, not merely whether one exists.
Your task

Design the algorithm and data structures.

Target approximately:

O(N log N)
 */

public class APIRateCapacityAnalyzer {

    private static final int START_DELTA = 1;
    private static final int END_DELTA = -1;

    private static final String START = "start";
    private static final String END = "end";

    public Result analyze(List<Rule> rules, long maxCapacity)
    {
        List<Event> events = new ArrayList<>();

        for(Rule rule : rules){
            events.add(new Event(rule.customerId, rule.startTime, rule.capacity * START_DELTA, START));
            events.add(new Event(rule.customerId, rule.endTime, rule.capacity * END_DELTA, END));
        }

        events.sort( (a, b) -> {
            if(a.time != b.time){
                return Long.compare(a.time, b.time); //asc time
            }

            return Long.compare(a.delta, b.delta); //end before start
        } );

        Set<String> users = new HashSet<>();
        int currCapacity = 0;

        for(Event event : events){
            if(event.type == START){
                users.add(event.customerId);
            }else{
                users.remove(event.customerId);
            }

            currCapacity += event.delta;

            if(currCapacity > maxCapacity){
                return new Result(event.time, users);
            }
        }

        return null;
    }

    static class Event{
        String customerId;
        long time;
        long delta;
        String type;

        public Event(String customerId, long time, long delta, String type) {
            this.customerId = customerId;
            this.time = time;
            this.delta = delta;
            this.type = type;
        }
    }

    static class Rule {
        String customerId;
        long startTime;
        long endTime;
        long capacity;

        Rule(String customerId, long startTime, long endTime, long capacity) {
            this.customerId = customerId;
            this.startTime = startTime;
            this.endTime = endTime;
            this.capacity = capacity;
        }
    }

    static class Result {
        long timestamp;
        Set<String> customers;

        Result(long timestamp, Set<String> customers) {
            this.timestamp = timestamp;
            this.customers = customers;
        }
    }
}
