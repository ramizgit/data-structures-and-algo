package intervals;

import java.util.*;

public class MinArrowsToBurstBalloons {

    //https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

    public int findMinArrowShots(int[][] points)
    {
        //input validation
        if(points == null || points.length == 0){
            return 0;
        }

        //sort input by end point
        Arrays.sort(points, (a, b) -> a[1] - b[1]);

        int arrow = 1;
        int lastPosEnd = points[0][1];

        for(int i=1; i<points.length; i++){
            int currPosStart = points[i][0];
            int currPosEnd = points[i][1];

            if(currPosStart > lastPosEnd){
                //no overlap, need new arrow
                arrow++;
                lastPosEnd = currPosEnd;
            }
        }

        return arrow;
    }
}
