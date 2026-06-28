package consistenthashing.lineSweep;

import java.util.*;

public class BrightestPositionOnStreet {

    //https://leetcode.com/problems/brightest-position-on-street/description/

    //trick: Use a sparse difference map (TreeMap) because the coordinate range is too large for a difference array.

    // Time : O(n log n)
    // Space: O(n)
    public int brightestPosition(int[][] lights)
    {
        TreeMap<Integer, Integer> diffMap = new TreeMap<>();

        for(int[] light : lights){

            int pos = light[0];
            int range = light[1];

            int left = pos - range;
            int right = pos + range;

            diffMap.put(left, diffMap.getOrDefault(left, 0) + 1);
            diffMap.put(right+1, diffMap.getOrDefault(right+1, 0) - 1); //right range in inclusive, hence right+!
        }

        //sweep using prefix sum
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
