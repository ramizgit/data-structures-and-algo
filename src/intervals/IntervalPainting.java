package consistenthashing.intervals;

/*
You are given a list of half-open intervals.

Each interval represents a segment on the number line in the form [start, end).

We paint the intervals one by one in the given order.

For each interval, return the size of the part that has not already been painted by any previous interval.

Return a list where the value at index i is the size of the newly painted segment contributed by the ith interval.

The size of a half-open interval [start, end) is end - start.

In this problem, each interval is provided as a string in the format "start,end".
Class
IntervalPainting
Method
amountPainted
List<Integer> amountPainted(List<String> intervals)
intervals.size() >= 1
Each interval string is in the format "start,end"
0 <= start < end
Each interval represents a half-open interval [start, end)
The answer at index i is the size of the segment not painted by previous intervals
Parameters
intervals
A list of strings where each string is in the format "start,end".

For example, "1,10" represents the half-open interval [1, 10).
Returns
Return a List<Integer> where each value represents how much of that interval was not painted before it was processed.
Constraints
1 <= intervals.size() <= 10^5
0 <= start < end <= 10^8
1 <= intervals.size()
Each intervals[i] contains exactly one comma
Each parsed interval satisfies 0 <= start < end
Intervals are processed in the given order
Previously painted parts must not be counted again
 */

import java.util.*;

public class IntervalPainting {

    //***GOOGLE***

    //todo:practice

    /*
    This is probably the most unusual use of Union-Find you'll encounter.
    We keep track of every unit inside the intervals and remember whether it has already been painted.
    We don't just store a boolean "painted"; we store a pointer to the next candidate unpainted position, which is what makes the algorithm efficient.
     */
    public List<Integer> amountPainted(List<String> intervals) {

        Map<Integer, Integer> parent = new HashMap<>();

        List<Integer> result = new ArrayList<>();

        for (String interval : intervals) {

            String[] parts = interval.split(",");

            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);

            int newlyPainted = 0;

            int pos = find(parent, start);

            while (pos < end) {

                newlyPainted++;

                // Mark this position as painted by pointing it to the next position
                parent.put(pos, pos + 1);

                // Jump directly to the next unpainted position
                pos = find(parent, pos);
            }

            result.add(newlyPainted);
        }

        return result;
    }

    private int find(Map<Integer, Integer> parent, int x) {

        if (!parent.containsKey(x)) {
            return x;
        }

        int next = find(parent, parent.get(x));

        parent.put(x, next);      // Path compression

        return next;
    }
}