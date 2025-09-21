package binarysearch;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args)
    {
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); //2.5
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4,5})); //2.5
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        //make sure nums1 is smaller to run binary search on it
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }

        int l1 = nums1.length;
        int l2 = nums2.length;
        int totalLen = l1 + l2;
        int haflLen = totalLen / 2;

        //run BS on nums1
        int left = 0;
        int right = l1-1;

        while(left <= right){
            int mid1 = (left + right) / 2;
            int mid2 = haflLen - mid1 - 2;

            int left1 = mid1 >=0 ? nums1[mid1] : Integer.MIN_VALUE;
            int right1 = mid1 < nums1.length-1 ? nums1[mid1+1] : Integer.MAX_VALUE;
            int left2 = mid2 >=0 ?nums2[mid2] : Integer.MIN_VALUE;
            int right2 = mid2 < nums2.length-1 ? nums2[mid2+1] : Integer.MAX_VALUE;

            if(left1 <= right2 && left2 <= right1){
                if(totalLen %2 == 0){
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }else{
                    return Math.min(right1, right2);
                }
            } else if (left1 > right2) {
                right = mid1 - 1;
            } else {
                left = mid1 + 1;
            }
        }
        return 0.0;
    }
}

