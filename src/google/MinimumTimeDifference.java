package google;

import java.util.*;

//todo : practice

public class MinimumTimeDifference {

    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] mins = new int[n];

        // Step 1: convert to minutes
        for (int i = 0; i < n; i++) {
            String t = timePoints.get(i);
            int h = Integer.parseInt(t.substring(0, 2));
            int m = Integer.parseInt(t.substring(3));
            mins[i] = h * 60 + m;
        }

        // Step 2: sort
        Arrays.sort(mins);

        int minDiff = Integer.MAX_VALUE;

        // Step 3: check adjacent differences
        for (int i = 1; i < n; i++) {
            minDiff = Math.min(minDiff, mins[i] - mins[i - 1]);
        }

        // Step 4: circular wrap using modulo
        int wrapDiff = (mins[0] - mins[n - 1] + 1440) % 1440;
        minDiff = Math.min(minDiff, wrapDiff);

        return minDiff;
    }
}
