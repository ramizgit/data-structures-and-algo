package google;

import java.util.*;

public class CountSquares {

    /*
    axis-aligned squares when all input points are unique.
     */

    public static void main(String[] args)
    {
        int[][] points = {
                {1, 1},
                {1, 3},
                {3, 1},
                {3, 3}
        };

        int result = countSquares(points);

        System.out.println("Number of squares = " + result);
    }

    public static int countSquares(int[][] points)
    {
        Set<String> set = new HashSet<>();

        for(int[] p : points){
            set.add(p[0]+ "," + p[1]);
        }

        int sqCount = 0;

        for(int i=0; i<points.length; i++){
            for(int j=i+1; j<points.length; j++){
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];

                //check vertical
                if(x1 == x2 && y1 != y2){
                    int d = Math.abs(y1 - y2);

                    //check right side
                    if(set.contains((x1+d) + "," + y1) && set.contains((x1+d) + "," + y2)){
                        sqCount++;
                    }
                }
            }
        }

        return sqCount;
    }
}
