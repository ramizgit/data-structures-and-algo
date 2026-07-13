package consistenthashing.lineSweep;

import java.util.*;

public class BrightestPositionOnStreet {

    //https://leetcode.com/problems/brightest-position-on-street/description/

    //trick: Use a sparse difference map (TreeMap) because the coordinate range is too large for a difference array.

    // Time : O(n log n)
    // Space: O(n)
    public int brightestPosition(int[][] lights)
    {
        //convert each range into difference map using start (+1) and end (-1)
        TreeMap<Integer, Integer> diffMap = new TreeMap<>();

        for(int[] light : lights){

            int pos = light[0];
            int range = light[1];

            int start = pos - range;
            int end = pos + range;

            diffMap.put(start, diffMap.getOrDefault(start, 0) + 1);
            diffMap.put(end+1, diffMap.getOrDefault(end+1, 0) - 1); //range in inclusive, hence end + 1
        }

        //sweep through the difference map using prefix sum
        int currBrightness = 0;
        int maxBrightness = 0;
        int brightestPosition = 0;

        for(Map.Entry<Integer, Integer> entry : diffMap.entrySet()){
            currBrightness += entry.getValue();

            if(currBrightness > maxBrightness){
                maxBrightness = currBrightness;
                brightestPosition = entry.getKey();
            }
        }

        return brightestPosition;
    }
}
