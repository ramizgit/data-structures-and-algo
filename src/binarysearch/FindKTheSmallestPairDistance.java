package binarySearch;

import java.util.*;

public class FindKTheSmallestPairDistance {

    //https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/

    /*
    Appraoch:-
    1. Brute force : generate all pair distance, then sort them, return kth distance in the sorted order. O(n^2)

    2. Binary search : binary search on the distance, then counts valid pairs indirectly using sliding window
     */

    public int smallestDistancePair(int[] nums, int k)
    {
        //sort input array
        Arrays.sort(nums);

        //binary search
        int low = 0; //min possible distance
        int high = nums[nums.length-1] - nums[0]; //max possible distance, larget - smallest
        int answer = 0;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(countPairs(nums, mid) >= k){
                answer = mid; //possible answer
                high = mid - 1; //try lower
            }else{
                low = mid + 1;
            }
        }

        return answer;
    }

    private int countPairs(int[] nums, int maxDistance)
    {
        int left = 0;
        int right = 0;
        int count = 0;

        while(right < nums.length){

            while(nums[right] - nums[left] > maxDistance){
                left++; //shrink window if distance is more than max dist.
            }

            count += right - left;

            right++;
        }

        return count;
    }
}
