package slidingWindow;

public class BinarySubarraysWithSum {

    //https://leetcode.com/problems/binary-subarrays-with-sum/description/
    /*
    Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
    A subarray is a contiguous part of the array.
     */

    /*
    All positive (> 0) → direct sliding window works for sum == K.
    0 and positive (>= 0) → atMost(K) - atMost(K-1) as direct (sum == K) count++ can miss multiple valid windows;
    Negative numbers allowed → sliding window generally doesn't work; use prefix sum + HashMap for exact-sum counting.
     */

    public int numSubarraysWithSum(int[] nums, int goal)
    {
        return numSubarraysWithSumAtMostK(nums, goal) - numSubarraysWithSumAtMostK(nums, goal-1);
    }

    private int numSubarraysWithSumAtMostK(int[] nums, int k)
    {
        if (k < 0) {
            return 0;
        }

        int windowStart = 0;
        int sum = 0;
        int count = 0;

        for(int windowEnd = 0; windowEnd < nums.length; windowEnd++){

            sum += nums[windowEnd];

            //shrink window as long as sum > k
            while(sum > k){
                sum -= nums[windowStart];
                windowStart++;
            }

            //now window sum is at most k, add num of subarrays to count
            count += windowEnd - windowStart + 1;
        }

        return count;
    }
}
