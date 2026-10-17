package binarysearch.kth;

public class KthSmallestSubarraySum {

    //https://leetcode.com/problems/kth-smallest-subarray-sum/description/

    //Time : O(n log(high-low))
    public int kthSmallestSubarraySum(int[] nums, int k)
    {
        //input validation
        if(nums == null || nums.length == 0 || k <= 0){
            return -1;
        }

        int n = nums.length;
        long totalSubarrays = (long) n * (n + 1) / 2;
        if (k > totalSubarrays) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        int total = 0;

        for(int num : nums){
            min = Math.min(min, num);
            total += num;
        }

        //binary search
        int low = min; //min possible sub array sum
        int high = total; //max possible sub array sum
        int answer = 0;

        while(low <= high){ //O(log(high-low)

            int mid = low + (high - low) / 2;

            //find how many subarrays have a sum at most mid
            if (countSubArrayWithSumAtMost(nums, mid) >= k) {
                //at least k subarrays fit under this sum limit. The answer could be mid or smaller.
                answer = mid;      // Valid candidate
                high = mid - 1;    //try smaller
            } else {
                //too few subarrays fit under this limit. The answer must be larger.
                low = mid + 1;     //try higher
            }
        }

        return answer;
    }

    //O(n)
    private int countSubArrayWithSumAtMost(int[] nums, int targetSum)
    {
        int windowStart = 0;
        int sum = 0;
        int count = 0;

        for(int windowEnd=0; windowEnd < nums.length; windowEnd++){

            sum += nums[windowEnd]; //expand window

            while(sum > targetSum){
                //shrink window
                sum -= nums[windowStart];
                windowStart++;
            }

            count += windowEnd - windowStart + 1;
        }

        return count;
    }
}
