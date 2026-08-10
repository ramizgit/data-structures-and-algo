package prefixSum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumDivisibleByK {

    //https://leetcode.com/problems/subarray-sums-divisible-by-k/description/

    /*
    Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
    A subarray is a contiguous part of an array.
     */

    private static int subarraysDivByK(int[] nums, int k)
    {
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();

        prefixSumFreq.put(0, 1); //base case to handle cases where a valid subarray starts at index 0
        int sum = 0;
        int count = 0;

        for(int i=0; i<nums.length; i++){

            sum += nums[i];

            //int remain = sum % k;
            //normalize negative remainder to [0, k-1]
            int remain = ((sum % k) + k) % k;

            count += prefixSumFreq.getOrDefault(remain, 0);
            prefixSumFreq.put(remain, prefixSumFreq.getOrDefault(remain, 0) + 1);

        }

        return count;
    }
}

