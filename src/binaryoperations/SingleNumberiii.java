package binary;

import java.util.*;

public class SingleNumberiii {

    //https://leetcode.com/problems/single-number-iii/description/

    /*
    Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
    Find the two elements that appear only once. You can return the answer in any order.
    You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
     */

    private static int[] singleNumber(int[] nums)
    {
        // 1. XOR all numbers -> a ^ b
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // 2. Get rightmost set bit (where a and b differ)
        int diffBit = xor & -xor;

        // 3. Partition numbers and XOR separately
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & diffBit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }
}
