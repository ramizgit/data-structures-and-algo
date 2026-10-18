package binarysearch.answersearch;

public class SplitArrayLargestSum {

    //https://leetcode.com/problems/split-array-largest-sum/description/
    
    private static int splitArray(int[] nums, int k)
    {
        int max = 0;
        int total = 0;

        for(int num : nums){
            max = Math.max(max, num);
            total += num;
        }

        //binary search
        int low = max; //lower bound : minimum possible answer is the largest element
        int high = total; //upper bound : maximum possible answer is the sum of the entire array

        int answer = 0;

        while(low <= high){

            int mid = low + (high - low) / 2;

            if (countSubarraysForThreshold(nums, mid) <= k) {
                //if we can split into <= k subarrays with max sum mid, we can always further split to make exactly k subarrays
                answer = mid; //possible answer
                high = mid - 1;  //try lower to minimize answer
            } else {
                low = mid + 1;   //try higher
            }
        }

        return answer;
    }

    private static int countSubarraysForThreshold(int[] nums, int maxSum)
    {
        int sum = 0;
        int subarrays = 1; //before we process any elements, we're already building the first subarray

        for(int num : nums){
            sum += num;

            if(sum > maxSum){
                subarrays++;
                sum = num; //reset
            }
        }

        return subarrays;
    }
}
