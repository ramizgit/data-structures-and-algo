package BinaryOps;

public class SingleNumberii {

    //https://leetcode.com/problems/single-number-ii/description/
    /*
    Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
    You must implement a solution with a linear runtime complexity and use only constant extra space.
     */

    public int singleNumber(int[] nums)
    {
        /*
        Appraoch :-
        Loop through all 32 bits, and for each bit count how many numbers have that bit set
            if count % 3 != 0
            → set that bit in result
         */
        int result = 0;

        for(int i=0; i<32; i++){
            
            int count = 0;

            for(int num : nums){

                if( (num & (1 << i)) != 0){
                    count++;
                }
            }

            if(count % 3 != 0){
                result = result | (1 << i); //set ith bit in the result

            }
        }

        return result;
    }
}
