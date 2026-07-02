package array;

public class RotatedSortedArray {

    private static int findPivotElement(int[] arr)
    {
        int low = 0;
        int high = arr.length-1;

        while (low <= high){
            int mid = low + (high - low)/2;

            if( mid == 0 || mid == arr.length-1 || (arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]) ){
                System.out.println("pivot idx : "+mid);
                return arr[mid];
            } else if (arr[mid] > arr[high]) {
                //we are in left sorted, move right
                low = mid + 1;
            }else{
                //we are in right sorted, move left
                high = mid - 1;
            }
        }

        return -1;
    }

    public static int findElement(int[] arr, int target)
    {
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while(low <= high){
            mid = (low + high) >>> 1;
            //mid = (high - low)/2 + low;

            if(arr[mid] == target){
                //pivot element found
                return mid;
            }else {
                if(arr[mid] < arr[high]){
                    //we are in right sorted array
                    if(target > arr[mid] &&  target <= arr[high]){
                        low = mid + 1;
                    }else {
                        high = mid - 1;
                    }
                }else {
                    //we are in left sorted array
                    if(target >= arr[low] &&  target < arr[mid]){
                        high = mid - 1;
                    }else {
                        low = mid + 1;
                    }
                }
            }
        }

        return -1;
    }
}
