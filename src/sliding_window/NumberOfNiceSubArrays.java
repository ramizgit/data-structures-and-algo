package slidingWindow;

public class NumberOfNiceSubArrays {

    //https://leetcode.com/problems/count-number-of-nice-subarrays/description/
    /*
    Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
    Return the number of nice sub-arrays.
     */
    public int numberOfSubarrays(int[] nums, int k)
    {
        int n = nums.length;
        int[] numsOddEven = new int[n];

        for(int i=0; i<n; i++){
            numsOddEven[i] = nums[i] % 2;
        }

        return countSubArrayWithSumAtMostK(numsOddEven, k) - countSubArrayWithSumAtMostK(numsOddEven, k-1);
    }

    private int countSubArrayWithSumAtMostK(int[] nums, int k)
    {
        int left = 0;
        int right = 0;
        int count = 0;
        int sum = 0;

        while(right < nums.length){
            sum += nums[right];

            while(sum > k){
                //shrink window
                sum -= nums[left];
                left++;
            }

            //count all sub array with sum at most k
            count += right - left + 1;

            right++;
        }

        return count;
    }
}
