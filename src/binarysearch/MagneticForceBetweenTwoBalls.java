package binarysearch;

import java.util.Arrays;

public class MagneticForceBetweenTwoBalls {

    //https://leetcode.com/problems/magnetic-force-between-two-balls/description/

    public static void main(String[] args)
    {
        //position = [1,2,3,4,7], m = 3
        System.out.println(maxDistance(new int[]{1,2,3,4,7}, 3));
        System.out.println(maxDistance(new int[]{1, 3, 5, 6, 7}, 3));

    }

    private static int maxDistance(int[] position, int m)
    {
        //sort inputs if not already
        Arrays.sort(position);

        int left = 1; //position[1] - position[0];
        int right = position[position.length - 1] - position[0];
        int answer = 0;

        while(left <= right){
            int mid = left + (right - left)/2;

            if(canPlace(position, m, mid)){
                answer = Math.max(answer, mid);
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean canPlace(int[] position, int m, int dist){
        //always place first ball at the start
        int pos = position[0];
        int count = 1;

        for(int i=1; i<position.length; i++){
            if(position[i] - pos >= dist){
                count++;

                //rest pos
                pos = position[i];
            }

            //if condition met, exit
            if(count == m){
                return true;
            }
        }
        return false;
    }
}
