package twopointer;

import java.util.Arrays;

public class BoatsToSavePeople {

    //https://leetcode.com/problems/boats-to-save-people/description/

    private static int numRescueBoats(int[] people, int limit)
    {
        Arrays.sort(people);

        int left = 0;
        int right = people.length-1;
        int numOfBoats = 0;

        while (left <= right){
            if(people[right] + people[left] <= limit){
                //both can share one boat
                right--;
                left++;
            }else {
                right--; //heaviest person must go alone.
            }
            
            numOfBoats++;
        }

        return numOfBoats;
    }
}
