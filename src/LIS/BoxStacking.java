package consistenthashing.LIS;

import java.util.*;

public class BoxStacking {

    private static final int L = 0;
    private static final int W = 1;
    private static final int H = 2;

    public int maxHeight(int[] height, int[] width, int[] length, int n) {

        // generate all rotations as it is also allowable to use multiple instances of the same type of box.
        // also do dimension sort for consistency so base dimensions satisfy L >= W
        List<int[]> boxes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // rotation 1: height[i] is height
            boxes.add(createBox(length[i], width[i], height[i]));

            // rotation 2: width[i] is height
            boxes.add(createBox(height[i], length[i], width[i]));

            // rotation 3: length[i] is height
            boxes.add(createBox(height[i], width[i], length[i]));
        }

        // Step 2 [re-arrange to put smaller cuboids first]: sort by base (l, w) ascending (LIS style)
        //this ensures when we process a box, all possible boxes that can go above it are already processed, primarily for DP concept
        boxes.sort( (a, b) -> {
           if(a[L] != b[L]){
               return a[L] - b[L]; // length
           }else if(a[W] != b[W]){
               return a[W] - b[W]; //width
           }else{
               return a[H] - b[H]; //height
           }
        });

        int m = boxes.size();

        // Step 3: LIS on height
        int[] dp = new int[m]; // dp[i] = maximum stack height with box i as the bottom box
        for (int i = 0; i < m; i++) {
            dp[i] = boxes.get(i)[H]; //base case
        }

        int max = 0;

        for (int i = 0; i < m; i++) {
            int[] curr = boxes.get(i);
            for (int j = 0; j < i; j++) {
                int[] prev = boxes.get(j);

                if (prev[L] < curr[L] //length
                        && prev[W] < curr[W] //width
                ) {
                    dp[i] = Math.max(dp[i], dp[j] + curr[H]);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private int[] createBox(int x, int y, int h) {
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
        return new int[]{l, w, h};
    }
}
