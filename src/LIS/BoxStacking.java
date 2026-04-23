package longestIncreasingSubseqVariants;

import java.util.*;

//todo : practice

public class BoxStacking {

    public int maxHeight(int[] height, int[] width, int[] length, int n) {

        // Step 1: generate all rotations
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
        Collections.sort(boxes);

        int m = boxes.size();

        int[] dp = new int[m];
        // Step 3: LIS on height
        for (int i = 0; i < m; i++) {
            dp[i] = boxes.get(i).h;
        }

        int max = 0;

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i; j++) {
                Box prev = boxes.get(j);
                Box curr = boxes.get(i);

                if (prev.l < curr.l && prev.w < curr.w) {
                    dp[i] = Math.max(dp[i], dp[j] + curr.h);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private Box createBox(int x, int y, int h) {
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
