package binarySearch;

/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the kth element of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).
 */

public class KthElementOfTwoSortedArrays {

    //similar to : https://leetcode.com/problems/median-of-two-sorted-arrays/

    public double findKthElementOfSortedArrays(int[] nums1, int[] nums2, int k)
    {
        //make sure first arr is smaller, to run binary search on it
        if(nums1.length > nums2.length){
            return findKthElementOfSortedArrays(nums2, nums1, k);
        }

        int m = nums1.length;
        int n = nums2.length;

        //run binary search on nums1 length (not index)
        int low = Math.max(0, k - n); //[low tells minimum elements nums1 MUST contribute]
        //if nums2 already has enough elements for left partition, A may contribute 0 elements

        int high = Math.min(k, m); //[high tells maximum elements nums1 CAN contribute]
        //mid1 <= k && mid1 <= m, hence high bound is Math.min(k, m)

        while(low <= high){
            int mid1 = low + (high - low) / 2;
            int mid2 = k - mid1;

            //note : mid1 is NOT an index, it represents how many elements are taken into left partition
            int left1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int right1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];
            int left2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int right2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];

            // correct partition
            if(left1 <= right2 && left2 <= right1) {
                    return Math.max(left1, left2);
            }else if (left1 > right2) {
                high = mid1 - 1; //took too many elements from nums1, move left
            } else {
                low = mid1 + 1; //left2 > right1 -> need more elements on left side from nums1, move right
            }
        }

        return 0.0;
    }
}
