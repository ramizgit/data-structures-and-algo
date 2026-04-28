package geometry;

import java.util.*;

public class MaxPointsOnALine {

    //https://leetcode.com/problems/max-points-on-a-line/description/

    /*
    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
    return the maximum number of points that lie on the same straight line.
     */
    public int maxPoints(int[][] points)
    {
        //hint : points with same slope will be on the same line

        int n = points.length;

        int max = 0;

        //fix one point → count slopes relative to THAT point
        for(int i=0; i<n; i++){
            /*
            The hashmap is initialized inside the loop because slope counts are specific to each anchor point;
            sharing it would mix unrelated slopes and give incorrect results.
             */
            Map<String, Integer> slope = new HashMap<>();
            int duplicates = 0;
            int localMax = 0;

            int x1 = points[i][0];
            int y1 = points[i][1];

            for(int j=i+1; j<n; j++){

                int x2 = points[j][0];
                int y2 = points[j][1];

                //find slope
                int diffX = x1 - x2;
                int diffY = y1 - y2;

                // duplicate point
                if (diffX == 0 && diffY == 0) {
                    duplicates++;
                    continue;
                }

                int gcd = gcd(Math.abs(diffX), Math.abs(diffY));

                diffX /= gcd;
                diffY /= gcd;

                // normalize sign
                if (diffX < 0) {
                    diffX *= -1;
                    diffY *= -1;
                }

                // vertical line
                if (diffX == 0) diffY = 1;

                // horizontal line
                if (diffY == 0) diffX = 1;

                String key = diffX + "," + diffY;
                slope.put(key, slope.getOrDefault(key, 0) + 1);

                localMax = Math.max(localMax, slope.get(key));
            }

            max = Math.max(max, localMax + duplicates + 1);
        }

        return max;
    }

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
