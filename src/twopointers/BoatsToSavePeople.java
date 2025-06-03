package twopointer;

import java.util.Arrays;

public class BoatsToSavePeople {

    //https://leetcode.com/problems/boats-to-save-people/description/

    public static void main(String[] args)
    {
        System.out.println(numRescueBoats(new int[]{2,4,6,9}, 10)); //3
        System.out.println(numRescueBoats(new int[]{1,4,6,9}, 10)); //2
        System.out.println(numRescueBoats(new int[]{1,4,5,6,9}, 10)); //3
    }

    private static int numRescueBoats(int[] people, int limit)
    {
        Arrays.sort(people);

        int left = 0;
        int right = people.length-1;
        int numOfBoats = 0;

        while (left <= right){
            if(people[right] + people[left] <= limit){
                right--;
                left++;
            }else {
                right--;
            }
            numOfBoats++;
        }

        return numOfBoats;
    }
}
