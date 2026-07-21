package greedy;

import java.util.*;

public class HandOfStraights {

    //https://leetcode.com/problems/hand-of-straights/description/

    /*
    Constraints:
        1 <= hand.length <= 1000
        0 <= hand[i] <= 1000
        1 <= groupSize <= hand.length
     */

    public boolean isNStraightHand(int[] hand, int groupSize)
    {

        //input validation
        if(hand == null || hand.length ==0 || groupSize == 0){
            return false;
        }

        //array length should be divisible by groupSize
        if(hand.length % groupSize != 0){
            return false;
        }

        int[] freq = new int[1001]; //freq array : using array since range is small, otherwise use hash map

        for(int h : hand){
            freq[h]++;
        }

        Arrays.sort(hand);

        for(int num : hand){

            if(freq[num] == 0){
                continue;
            }

            for(int i=num; i<num+groupSize; i++){

                if(freq[i] == 0){
                    return false; //missing number
                }

                freq[i]--; //reduce freq
            }

        }

        return true;
    }
}
