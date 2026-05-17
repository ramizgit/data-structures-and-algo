package binary;

public class SingleNumberiii {

    //https://leetcode.com/problems/single-number-iii/

    /*
    Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
    Find the two elements that appear only once. You can return the answer in any order.
    You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
     */

    public int[] singleNumber(int[] nums)
    {
        //step1 : xor all numbers
        int xor = 0;
        for(int num : nums){
            xor ^= num;
        }

        //step2 : find right most set bit, as the two numbers differ at this bit position, dupes numbers cancel out
        int diffBit = xor & -xor;

        //step3 : partition numbers based on the diffBit and track xor separately
        int xor1 = 0;
        int xor2 = 0;
        for(int num : nums){
            if( (num & diffBit) == 0 ){
                xor1 ^= num;
            }else{
                xor2 ^= num;
            }
        }

        return new int[]{xor1, xor2};
    }
}
