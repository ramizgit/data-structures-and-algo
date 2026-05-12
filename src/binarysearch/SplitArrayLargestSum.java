package binarysearch;

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
        int low = max;
        int high = total;

        int answer = 0;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(canSplit(nums, k, mid)){
                answer = mid; //possible answer
                high = mid - 1; //try lower to minimize max sum
            }else{
                low = mid + 1; //try higher
            }
        }

        return answer;
    }

    //nums = [7,2,5,10,8], k = 2
    private static boolean canSplit(int[] nums, int k, int maxSum)
    {
        int sum = 0;
        int subarray = 1;

        for(int n : nums){
            sum += n;

            if(sum > maxSum){
                subarray++;
                //reset
                sum = n;
            }
        }

        // if we can split into <= k subarrays with max sum <= X,
        // we can always further split to make exactly k subarrays,
        // and the max sum will only stay same or decrease
        // hence we check subarray <= k

        return subarray <= k;
    }
}
