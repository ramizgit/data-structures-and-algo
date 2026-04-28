package geometry;

import java.util.HashSet;
import java.util.Set;

public class CountSquares {

    /*
    axis-aligned squares when all input points are unique.
     */

    //APPROACH : PICK TWO VERTICAL POINTS AND CHECK IF CORRESPONDING OPP. POINTS EXIST OR NOT
    public static int countSquares(int[][] points)
    {
        int n = points.length;

        //store points in hashset for quick O(1) lookup
        Set<String> pointsSet = new HashSet<>();
        for(int[] point : points){
            pointsSet.add(point[0]+ "," + point[1]);
        }

        int sqCount = 0;

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){

                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];

                //check vertical
                if(x1 == x2 && y1 != y2){
                    int d = Math.abs(y1 - y2);

                    //check right side
                    if(pointsSet.contains((x1+d) + "," + y1) && pointsSet.contains((x1+d) + "," + y2)){
                        sqCount++;
                    }
                }
            }
        }

        return sqCount;
    }
}
