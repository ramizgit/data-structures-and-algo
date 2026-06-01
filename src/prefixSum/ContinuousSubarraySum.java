package consistenthashing.prefixSum;

import java.util.*;

public class ContinuousSubarraySum {

    //https://leetcode.com/problems/continuous-subarray-sum/description/

    /*
    Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
    A good subarray is a subarray where:
    its length is at least two, and
    the sum of the elements of the subarray is a multiple of k.
     */

    public boolean checkSubarraySum(int[] nums, int k)
    {
        //input validation
        if(nums == null || nums.length == 0){
            return false;
        }

        int n = nums.length;

        Map<Integer, Integer> remainderIdxMap = new HashMap<>(); //remainder -> first index where remainder was seen
        remainderIdxMap.put(0, -1); //base case to handle cases where the valid subarray starts at index 0. e.g., [2,4]
        long sum = 0;

        for(int i=0; i<n; i++){
            sum += nums[i];

            int remainder = (int) sum % k;

            if(remainderIdxMap.containsKey(remainder)){
                if(i - remainderIdxMap.get(remainder) >= 2){ //length validation
                    return true;
                }
            }else{
                //store only the first occurrence of a remainder, as later occurrences might produce shorter subarrays and length validation might fail
                remainderIdxMap.put(remainder, i);
            }


        }

        return false;
    }
}
