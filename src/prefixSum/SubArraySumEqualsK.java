package prefixSum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    //https://leetcode.com/problems/subarray-sum-equals-k/description/

    /*
    Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
    A subarray is a contiguous non-empty sequence of elements within an array.
     */

    public static void main(String[] args)
    {
        // nums = [1,2,3], k = 3
        //<sum, idx>
        //0 - 1
        //1 - 1
        //3 - 1
        //6 - 1
        System.out.println(subarraySum(new int[]{1,2,3}, 3)); //2
        System.out.println(subarraySum(new int[]{1,2,3,3}, 3)); //3
        System.out.println(subarraySum(new int[]{1,-1,1,1,1,1}, 3)); //4
    }

    private static int subarraySum(int[] nums, int k)
    {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        int sum = 0;
        int count = 0;

        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            count += prefixSum.getOrDefault((sum - k), 0);
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
