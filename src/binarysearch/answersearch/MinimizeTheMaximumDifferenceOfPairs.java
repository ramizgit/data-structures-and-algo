package binarysearch.answersearch;

import java.util.*;

public class MinimizeTheMaximumDifferenceOfPairs {

    //https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/

    //Time : O(n logn + n logD)
    public int minimizeMax(int[] nums, int p)
    {
        //input validation
        if(nums == null || nums.length == 0 || p == 0){
            return 0;
        }

        Arrays.sort(nums); //(O(nlog n))

        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        int answer = 0;

        while(low <= high){ //(O(n log(D))), where (D = max(nums)-min(nums))

            int mid = low + (high - low)/2;

            if(countNonOverlappingPairsAtMost(nums, mid) >= p){
                answer = mid; //possible answer
                high = mid - 1; //try lower to minimize the answer
            }else{
                low = mid + 1; //try higher
            }
        }

        return answer;
    }

    private int countNonOverlappingPairsAtMost(int[] nums, int threshold)
    {
        int i = 0;
        int count = 0;

        while(i < nums.length - 1){

            if(nums[i+1] - nums[i] <= threshold){
                count++;
                i += 2;
            }else{
                i += 1;
            }
        }

        return count;
    }
}

