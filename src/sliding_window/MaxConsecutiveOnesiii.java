package sliding_window;

public class MaxConsecutiveOnesiii {

    //https://leetcode.com/problems/max-consecutive-ones-iii/description/

    /*
    Given a binary array nums and an integer k,
    return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     */

    public int longestOnes(int[] nums, int k)
    {
        int windowStart = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for(int windowEnd=0; windowEnd < nums.length; windowEnd++){

            if(nums[windowEnd] == 0){
                zeroCount++;
            }

            //shrink window till we have at most k 0's in it
            while(zeroCount > k){

                if(nums[windowStart] == 0){
                    zeroCount--;
                }
                windowStart++;
            }

            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
        }

        return maxLen;
    }
}
