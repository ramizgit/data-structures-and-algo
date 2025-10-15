package binarysearch;

public class FindPeakElement {

    //https://leetcode.com/problems/find-peak-element/description/

    public static void main(String[] args)
    {
        System.out.println(findPeakElement(new int[]{1,2,3,1})); //2
        System.out.println(findPeakElement(new int[]{1,2,1,3,5,6,4})); //5
        System.out.println(findPeakElement(new int[]{2,1,3,4})); //3
        System.out.println(findPeakElement(new int[]{4,3,2,1})); //0
    }

    //todo:practice it, here left < high, not <=
    private static int findPeakElement(int[] nums)
    {
        int low = 0, high = nums.length - 1;

        while (low < high) {  // note: strictly <
            int mid = low + (high - low) / 2;
    
            // compare mid with its next element only
            if (nums[mid] > nums[mid + 1]) {
                // peak is on the left (including mid)
                high = mid;
            } else {
                // peak is on the right
                low = mid + 1;
            }
        }

        return low; // or high (both will be same)
    }

     private static int findPeakElement(int[] nums)
    {
        int low = 0;
        int high = nums.length-1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if((mid-1 < 0 || nums[mid-1] < nums[mid]) && (mid+1 >= nums.length || nums[mid] > nums[mid+1])){
                return mid;
            } else if (nums[mid + 1] > nums[mid]) {
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        return -1;
    }
}
