package consistenthashing.prefixSum;

import java.util.*;

public class MaxSubArraySumWithEqualFirstAndLast {

    //https://codezym.com/question/140

    public long maximumSumSubarray(int[] nums)
    {
        Map<Integer, Long> minPrefixSum = new HashMap<>();
        long sum = 0;
        long max = Long.MIN_VALUE;

        for(int i=0; i<nums.length; i++){

            max = Math.max(max, nums[i]);   // singleton subarray [i..i]

            sum += nums[i];

            if(minPrefixSum.containsKey(nums[i])){

                max = Math.max(max, sum - minPrefixSum.get(nums[i]) + nums[i]);

                //udpate if current sum is smaller
                if(sum < minPrefixSum.get(nums[i])){
                    minPrefixSum.put(nums[i], sum);
                }
            }else{
                minPrefixSum.put(nums[i], sum);
            }
        }

        return max;
    }
}
