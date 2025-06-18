package prefixSum;

import java.util.HashMap;
import java.util.Map;

public class ContiguousSubarray {
    //https://leetcode.com/problems/contiguous-array/description/

    public static void main(String[] args)
    {
        System.out.println(findMaxLength(new int[]{0,1}));
        System.out.println(findMaxLength(new int[]{0,1,0}));
        System.out.println(findMaxLength(new int[]{0,1,1,1,1,1,0,0,0}));
    }

    private static int findMaxLength(int[] nums)
    {
        Map<Integer, Integer> prefixSum = new HashMap<>(); //0(n) space complexity
        prefixSum.put(0, -1);
        int sum = 0;
        int maxLen = 0;

        for(int i=0; i<nums.length; i++){ //0(n) time complexity
            sum += (nums[i] == 0 ? -1 : 1);

            if(prefixSum.containsKey(sum)){
                maxLen = Math.max(maxLen, (i - prefixSum.get(sum)));
            }else {
                prefixSum.put(sum, i);
            }
        }

        return maxLen;
    }
}
