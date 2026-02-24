package slidingWindow;

public class MaxConsecutiveOnesiii {

    //https://leetcode.com/problems/max-consecutive-ones-iii/description/

    /*
    Given a binary array nums and an integer k,
    return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     */

    public int longestOnes(int[] nums, int k)
    {
        int left = 0;
        int right = 0;
        int zero = 0;
        int max = 0;

        while(right < nums.length){

            if(nums[right] == 0){
                zero++;
            }

            while(zero > k){
                if(nums[left] == 0){
                    zero--;
                }
                left++;
            }

            max = Math.max(max, right - left + 1);

            right++;
        }

        return max;
    }
}
