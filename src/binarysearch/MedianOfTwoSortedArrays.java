package binarySearch;

/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).
 */

public class MedianOfTwoSortedArrays {

    //https://leetcode.com/problems/median-of-two-sorted-arrays/

    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        //make sure first arr is smaller, to run binary search on it
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLen = m + n;
        int halfLen = (totalLen + 1) / 2;

        //run binary search on nums1 length (not index)
        int low = 0;
        int high = m; //binary search on length, not index (important)

        while(low <= high){
            int mid1 = low + (high - low) / 2;
            int mid2 = halfLen - mid1;

            //note : mid1 is NOT an index, it represents how many elements are taken into left partition
            int left1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int right1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];
            int left2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int right2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];

            // correct partition
            if(left1 <= right2 && left2 <= right1) {
                if(totalLen % 2 == 0){
                    // even length
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }else{
                    // odd length
                    return Math.max(left1, left2);
                }
            }else if (left1 > right2) {
                high = mid1 - 1; //took too many elements from nums1, move left
            } else {
                low = mid1 + 1; //left2 > right1 -> need more elements on left side from nums1, move right
            }
        }

        return 0.0;
    }
}
