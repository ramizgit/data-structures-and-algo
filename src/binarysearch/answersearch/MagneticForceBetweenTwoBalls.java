package binarysearch.answersearch;

import java.util.Arrays;

public class MagneticForceBetweenTwoBalls {

    //https://leetcode.com/problems/magnetic-force-between-two-balls/description/

    public int maxDistance(int[] position, int m)
    {
        //sort inputs if not already
        Arrays.sort(position);

        int left = 1; //min possible distance between two balls
        int right = position[position.length - 1] - position[0]; //max possible distnce between two balls
        int answer = 0;

        while(left <= right){
            int mid = left + (right - left)/2;

            if(canPlace(position, m, mid)){
                answer = mid; //possible answer
                left = mid + 1; //try higher to maximise distance
            }else{
                right = mid - 1; //cant place, try lower distance
            }
        }

        return answer;
    }

    private boolean canPlace(int[] position, int m, int dist) {
        int lastPlaced = position[0]; //always place first ball at the start
        int count = 1;
    
        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPlaced >= dist) {
                //place another ball at the current basket
                count++;
                lastPlaced = position[i]; //reset position

                //early exit
                if (count == m) {
                    return true;
                }
            }
        }
    
        return false;
    }
}
