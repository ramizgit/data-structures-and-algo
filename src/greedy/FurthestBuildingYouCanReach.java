package greedy;

import java.util.*;

public class FurthestBuildingYouCanReach {

    // https://leetcode.com/problems/furthest-building-you-can-reach/

    public int furthestBuilding(int[] heights, int bricks, int ladders) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int i = 1; i < heights.length; i++) {

            int bricksNeeded = heights[i] - heights[i - 1];

            if (bricksNeeded <= 0) {
                continue; // free jump, no bricks/ladder needed
            }

            if (bricks >= bricksNeeded) {
                //use bricks
                bricks -= bricksNeeded;
                maxHeap.offer(bricksNeeded);
            } else {
                //use ladder instead

                if (ladders == 0) {
                    return i - 1; //exhaused both bricks and ladder, return answer
                }

                ladders--; //consume ladder for the current climb

                //use ladder on whichever climb is larger
                if(!maxHeap.isEmpty() && maxHeap.peek() > bricksNeeded) {

                    //reassign ladder to previous largest climb
                    bricks += maxHeap.poll();

                    //use reclaimed bricks for current climb
                    bricks -= bricksNeeded;
                    maxHeap.offer(bricksNeeded);
                }
            }
        }

        return heights.length - 1;
    }
}