package prefixSum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumDivisibleByK {

    //https://leetcode.com/problems/subarray-sums-divisible-by-k/description/

    /*
    Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
    A subarray is a contiguous part of an array.
     */

    public static void main(String[] args)
    {
        //nums = [4,5,0,-2,-3,1], k = 5
        System.out.println(subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5)); //7

    }

    private static int subarraysDivByK(int[] nums, int k)
    {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        int sum = 0;
        int count = 0;

        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            int remain = sum % k;
            count += prefixSum.getOrDefault(remain, 0);
            prefixSum.put(remain, prefixSum.getOrDefault(remain, 0) + 1);

        }

        return count;
    }
}

