package consistenthashing.array;

public class SpecialArrayI {

    //https://leetcode.com/problems/special-array-i/description/

    /*
    An array is considered special if the parity of every pair of adjacent elements is different.
    In other words, one element in each pair must be even, and the other must be odd.
    You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.
     */

    public boolean isArraySpecial(int[] nums)
    {
        for(int i=0; i<nums.length-1; i++){

            if(nums[i] % 2 == nums[i+1] % 2){
                return false;
            }
        }

        return true;
    }
}
