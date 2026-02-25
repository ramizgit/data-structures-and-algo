package binarySearch;

import java.util.*;

public class Heaters {

    //https://leetcode.com/problems/heaters/description/

    //O(mlogm) + O(nlogm)
    public int findRadius(int[] houses, int[] heaters)
    {
        Arrays.sort(heaters); //O(mlogm)
        int result = 0;

        for(int house : houses){ //O(n)
            int heaterIdx = insertionPoint(heaters, house); //log(m)

            int nearestHeaterLeft = heaterIdx > 0 ? heaters[heaterIdx - 1] : Integer.MAX_VALUE;
            int nearestHeaterRight = heaterIdx < heaters.length ? heaters[heaterIdx] : Integer.MAX_VALUE;
            int nearestHeater = Math.min(nearestHeaterLeft, nearestHeaterRight);

            result = Math.max(result, Math.abs(house - nearestHeater));
        }

        return result;
    }

    private int insertionPoint(int[] heaters, int house)
    {
        int low = 0;
        int high = heaters.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            if(heaters[mid] == house){
                return mid;
            }else if(heaters[mid] > house){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return low;
    }
}
