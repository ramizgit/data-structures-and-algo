package binarySearch;

import java.util.*;

/*
Problem: Aggressive Cows

You are given:

an array stalls[] representing positions of stalls on a number line
an integer k representing number of cows

Place the cows in stalls such that the minimum distance between any two cows is maximized.

Return that maximum possible minimum distance.

Input:
stalls = [1,2,4,8,9]
k = 3

Output:
3
 */

public class AgressiveCows {

    //similar to https://leetcode.com/problems/magnetic-force-between-two-balls/description/

    //Time : O(n log n + n log(max-min))
    public int aggressiveCows(int[] stalls, int k)
    {
        //sort stalls
        Arrays.sort(stalls); //O(n log n)

        //binary search
        int low = 1; //min possible distance between two cows
        int high = stalls[stalls.length - 1] - stalls[0]; //max possible distance
        int answer = 0;

        while(low <= high){ //O(log(max-min))
            int mid = low + (high - low)/2;

            if(canPlace(stalls, k, mid)){ //O(n)
                answer = mid; //possible answer
                low = mid + 1; //try higher to maximise distnce
            }else{
                high = mid - 1; //cant place, try lower distance
            }
        }

        return answer;
    }

    private boolean canPlace(int[] stalls, int k, int distance)
    {
        int lastPlaced = stalls[0]; //always place first cow at the start
        int count = 1;

        for(int i=1; i<stalls.length; i++){
            if( (stalls[i] - lastPlaced) >= distance){
                //place another cow at current stall
                count++;
                lastPlaced = stalls[i];

                //early exit
                if(count == k){
                    return true;
                }
            }
        }

        return count >= k;
    }
}
