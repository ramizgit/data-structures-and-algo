package array;

public class RotatedSortedArray {
    public static void main(String[] args)
    {
        System.out.println("=========PIVOT============");
        System.out.println(getPivotElement(new int[]{10,11,12,2,3,5,6,8,9})); //2
        System.out.println(getPivotElement(new int[]{10,1,2,3,4,5,6,7,8,9})); //1
        System.out.println(getPivotElement(new int[]{10,11,12,13,14,15,16,17,5})); //5
        System.out.println(getPivotElement(new int[]{10,11,12,13,14,15,16,17,5,6})); //5

        System.out.println("=========FIND============");
        System.out.println(findElement(new int[]{10,11,12,2,3,5,6,8,9}, 8)); //7
        System.out.println(findElement(new int[]{10,1,2,3,4,5,6,7,8,9}, 1)); //1
        System.out.println(findElement(new int[]{10,11,12,13,14,15,16,17,5}, 14)); //4
        System.out.println(findElement(new int[]{10,11,12,13,14,15,16,17,5,6}, 5)); //8
        System.out.println(findElement(new int[]{10,11,12,13,14,15,16,17,5,6}, 6)); //9
        System.out.println(findElement(new int[]{10,11,12,13,14,15,16,17,5,6}, 10)); //0
        System.out.println(findElement(new int[]{10,11,12,13,14,15,16,17,5,6}, 26)); //-1
    }

    public static int getPivotElement(int[] arr)
    {
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while(low <= high){
            mid = (low + high) >>> 1;
            //mid = (high - low)/2 + low;

            if(mid == arr.length-1 || arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]){
                //pivot element found
                return arr[mid];
            }else if(arr[mid] < arr[low] && arr[mid] < arr[high]){
                //we are in right sorted array, reduce high pointer
                high = mid - 1;
            }else {
                //we are in left sorted array, increase low pointer
                low = mid + 1;
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
