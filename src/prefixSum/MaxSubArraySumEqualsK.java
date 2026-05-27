package prefixSum;

import java.util.HashMap;
import java.util.Map;

public class MaxSubArraySumEqualsK {
    
    //https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
  
    private static int maxSubArray(int[] arr, int target)
    {
       Map<Integer, Integer> sumIdxMap = new HashMap<>();
        sumIdxMap.put(0, -1); //base case
        int sum = 0;
        int max = 0;

        for(int i=0; i<nums.length; i++){
            sum += nums[i];

            if(sumIdxMap.containsKey(sum - k)){
                max = Math.max(max, i - sumIdxMap.get(sum - k));
            }

            // store "first" occurrence only for maximum sub array length
            sumIdxMap.putIfAbsent(sum, i);
        }

        return max;
    }
}
