package geometry;

import java.util.HashSet;
import java.util.Set;

public class MinimumAreaRectangle {

    //https://leetcode.com/problems/minimum-area-rectangle/description/

    /*
    You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
    Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes.
    If there is not any such rectangle, return 0.
     */


    //APPROACH : pick two diagonally opp. points and check if other two corresponding diagonal points exists or not
    //Time : O(n²), Space : O(n)
    public int minAreaRect(int[][] points)
    {
        int n = points.length;

        //store points in hashset for quick O(1) lookup
        Set<String> pointsSet = new HashSet<>();
        for(int[] point : points){
            pointsSet.add(point[0]+","+point[1]);
        }

        int minArea = Integer.MAX_VALUE;

        //iterate all pairs, check if rectangle feasbile, calculate area
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int[] p1 = points[i];
                int[] p2 = points[j];

                int x1 = p1[0];
                int y1 = p1[1];
                int x2 = p2[0];
                int y2 = p2[1];

                if (x1 == x2 || y1 == y2) {
                    continue; //ignore pairs that are not diagonals
                }

                //check existence of diagonally opposite points
                String diagonal1 = x1+","+y2; //{x1, y2};
                String diagonal2 = x2+","+y1;// {x2, y1};

                if(pointsSet.contains(diagonal1) && pointsSet.contains(diagonal2)){
                    int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);
                    minArea = Math.min(minArea, area);
                }
            }
        }

        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}
