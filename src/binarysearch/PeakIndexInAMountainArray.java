package binarysearch;

public class PeakIndexInAMountainArray {
    public static void main(String[] args)
    {
        System.out.println(findPeak(new int[]{0,1,0})); //1
        System.out.println(findPeak(new int[]{0,1,2,1,0})); //2
        System.out.println(findPeak(new int[]{0,10,5,2})); //1
        System.out.println(findPeak(new int[]{0,2,5,3,1})); //2
    }

    private static int findPeak(int[] arr)
    {
        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            int mid = left + (right - left)/2;

            if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]){
                return mid;
            }else if(arr[mid] < arr[mid+1]){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return -1;
    }
}
