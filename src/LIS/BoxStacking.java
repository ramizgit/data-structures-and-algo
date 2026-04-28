package longestIncreasingSubseqVariants;

import java.util.*;

//todo : practice

public class BoxStacking {

    public int maxHeight(int[] height, int[] width, int[] length, int n) {

        // Step 1: generate all rotations as it is also allowable to use multiple instances of the same type of box.
        List<Box> boxes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // rotation 1: height[i] is height
            boxes.add(createBox(length[i], width[i], height[i]));

            // rotation 2: width[i] is height
            boxes.add(createBox(height[i], length[i], width[i]));

            // rotation 3: length[i] is height
            boxes.add(createBox(height[i], width[i], length[i]));
        }

        // Step 2: sort by base (l, w) ascending (LIS style)
        //this ensures when we process a box, all possible boxes that can go above it are already processed, primarily for DP concept
        Collections.sort(boxes);

        int m = boxes.size();

        // Step 3: LIS on height
        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = boxes.get(i).h; //base case
        }

        int max = 0;

        for (int i = 1; i < m; i++) {
            Box curr = boxes.get(i);
            for (int j = 0; j < i; j++) {
                Box prev = boxes.get(j);

                if (prev.l < curr.l && prev.w < curr.w) {
                    dp[i] = Math.max(dp[i], dp[j] + curr.h);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private Box createBox(int x, int y, int h) {
        /*
        We enforce length >= breadth to eliminate duplicate base orientations and ensure consistent comparisons during LIS/DP
        You guarantee:
            Consistent ordering
            Symmetry removed
            Valid comparisons in both dimensions
        If a cuboid can be rotated to fit somewhere, then its sorted version will also fit in that same position.
        */
        int l = Math.max(x, y);
        int w = Math.min(x, y);
        return new Box(l, w, h);
    }

    class Box implements Comparable<Box> {
        int l, w, h; // l >= w always

        Box(int l, int w, int h) {
            this.l = l;
            this.w = w;
            this.h = h;
        }

        @Override
        public int compareTo(Box other) {
            if (this.l != other.l) return this.l - other.l;
            if (this.w != other.w) return this.w - other.w;
            return this.h - other.h; // optional tie-break
        }
    }
}
