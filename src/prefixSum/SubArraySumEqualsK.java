package prefixSum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    //https://leetcode.com/problems/subarray-sum-equals-k/description/

    /*
    Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
    A subarray is a contiguous non-empty sequence of elements within an array.
     */

    //Time  : O(n)
    //Space : O(n)
    public int subarraySum(int[] nums, int k)
    {
        //create map to store {current sum -> freq}
        Map<Integer, Integer> prefixSumFreqMap = new HashMap<>();
        prefixSumFreqMap.put(0, 1); //base case to handle cases where a valid subarray starts at index 0. e.g., nums = [1, 2] for target 3

        int sum = 0;
        int count = 0;

        for(int num : nums){
            sum += num;

            //add num of subarrays ending at current index to the answer
            //num of subarray ending at current index is prev subarrays with sum (sum-k)
            count += prefixSumFreqMap.getOrDefault(sum - k, 0);

            prefixSumFreqMap.put(sum, prefixSumFreqMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
