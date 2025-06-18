package prefixSum;

import java.util.HashMap;
import java.util.Map;

public class MaxSubArraySumEqualsK {
    //https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
    public static void main(String[] args)
    {
        System.out.println(maxSubArray(new int[]{1,1,1,1,-1,-1,-1,-1,1,1,1,1,5}, 4));

    }

    private static int maxSubArray(int[] arr, int target)
    {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, -1);
        int sum = 0;
        int maxLen = 0;

        for(int i=0; i<arr.length; i++){
            sum += arr[i];

            if(prefixSum.containsKey(sum - target)){
                maxLen = Math.max(maxLen, (i - prefixSum.get(sum - target)));
            }

            if (!prefixSum.containsKey(sum)) {
                prefixSum.put(sum, i);
            }
        }

        return maxLen;
    }
}
