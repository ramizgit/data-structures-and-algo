package google;

import java.util.*;

public class MinimumAreaRectangleII {

    //https://leetcode.com/problems/minimum-area-rectangle-ii/description/

    /*
    You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
    Return the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the X and Y axes.
    If there is not any such rectangle, return 0.
     */

    public double minAreaFreeRect(int[][] points)
    {
        int n = points.length;

        //rectangles have diagonals with same length and same midpoint, hence group all such diagonal together
        Map<String, List<int[][]>> map = new HashMap<>();

        for(int i=0; i<n; i++){

            //point i coordinates
            int x1 = points[i][0];
            int y1 = points[i][1];

            for(int j=i+1; j<n; j++){

                //point j coordinates
                int x2 = points[j][0];
                int y2 = points[j][1];

                //diagonal mid point
                double midX = (x1 + x2) / 2.0;
                double midY = (y1 + y2) / 2.0;

                //diagonal length via pythagorean theorem
                int len = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);

                //map key : diagonal mid point + diagonal length
                String key = midX + "," + midY + "," + len;

                //map value : diagonal { point1, point2 }
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[][]{points[i], points[j]});
            }
        }

        double minArea = Double.MAX_VALUE;

        //for each group, form rectangles
        for(List<int[][]> group : map.values()){
            for(int i=0; i<group.size(); i++){

                //diagonal ith coordinates
                int[][] d1 = group.get(i);
                int[] A = d1[0];
                int[] B = d1[1];

                for(int j=i+1; j<group.size(); j++){

                    //diagonal jth coordinates
                    int[][] d2 = group.get(j);
                    int[] C = d2[0];
                    int[] D = d2[1];

                    // compute area using A as common point
                    double side1 = distance(A, C);
                    double side2 = distance(A, D);
                    double area = side1 * side2;

                    if (area > 0) {
                        minArea = Math.min(minArea, area);
                    }
                }
            }
        }

        return minArea == Double.MAX_VALUE ? 0 : minArea;
    }

    private double distance(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}
