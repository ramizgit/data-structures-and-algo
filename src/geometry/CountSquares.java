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

    /*
    squares are not axis aligned, they are rotated at any angle on the plane
     */
    
    /*
    APPROACH:-
     Pick any 2 points as a potential side AB.
     Compute vector (dx, dy). A square’s adjacent side is a perpendicular vector of same length.
     Rotate (dx, dy) by ±90° → (-dy, dx) and (dy, -dx) to get the only two possible squares on either side.
     Add this rotated vector to both A and B to get the other two points (C and D).
     If both points exist in the set → valid square.
     Each square is counted 4 times (once per side), so divide final count by 4.
     */
    public static int countSquares2(int[][] points) {
        int n = points.length;

        Set<String> set = new HashSet<>();
        for (int[] p : points) {
            set.add(p[0] + "," + p[1]);
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                int dx = x2 - x1;
                int dy = y2 - y1;

                // rotation 1
                int x3 = x1 - dy, y3 = y1 + dx;
                int x4 = x2 - dy, y4 = y2 + dx;

                if (set.contains(x3 + "," + y3) &&
                        set.contains(x4 + "," + y4)) {
                    count++;
                }

                // rotation 2
                x3 = x1 + dy; y3 = y1 - dx;
                x4 = x2 + dy; y4 = y2 - dx;

                if (set.contains(x3 + "," + y3) &&
                        set.contains(x4 + "," + y4)) {
                    count++;
                }
            }
        }

        // each square counted 4 times (each side)
        return count / 4;
    }

}
