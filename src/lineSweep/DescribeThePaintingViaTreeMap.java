package consistenthashing.lineSweep;

import java.util.*;

public class DescribeThePaintingViaTreeMap {

    //https://leetcode.com/problems/describe-the-painting/description/

    /*
    Approach:
    Use a TreeMap as a sparse difference map.
    Unlike a difference array, we only store coordinates where the paint changes,
    avoiding a scan over the entire coordinate range. Unlike an event list, the
    TreeMap automatically combines multiple color changes at the same coordinate
    while keeping all coordinates sorted for the sweep.
    */

    // Time : O(n log n)
    // Space: O(n)
    public List<List<Long>> splitPainting(int[][] segments)
    {
        //input validation
        if(segments == null || segments.length == 0){
            return Collections.emptyList();
        }

        TreeMap<Integer, Long> eventsMap = new TreeMap<>();

        // Build the difference map: +color at segment start, -color at segment end.
        for(int[] segment : segments){
            int start = segment[0];
            int end = segment[1];
            long color = segment[2];

            eventsMap.put(start, eventsMap.getOrDefault(start, 0L) + color);
            eventsMap.put(end, eventsMap.getOrDefault(end, 0L) - color);
        }

        // Sweep the difference map and build the painted intervals.
        List<List<Long>> result = new ArrayList<>();

        long currSum = 0;
        int prevIdx = -1;

        for (Map.Entry<Integer, Long> entry : eventsMap.entrySet()) {

            int currIdx = entry.getKey();

            // The previous color sum was valid in [prevX, currX)
            if (prevIdx != -1 && currSum != 0) {
                result.add(Arrays.asList(
                        (long) prevIdx,
                        (long) currIdx,
                        currSum));
            }

            // Apply the color change at the current coordinate.
            currSum += entry.getValue();
            prevIdx = currIdx;
        }

        return result;
    }
}
