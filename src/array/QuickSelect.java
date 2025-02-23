package array;

public class QuickSelect {

    public static void main(String[] args)
    {

    }

    private static int findKthLargest(int[] arr, int k)
    {
        k = arr.length - k;
        return quickSelect(arr, 0, arr.length-1, k);

    }

    private static int quickSelect(int[] arr, int left, int right, int k)
    {
        int p = left;

        for(int i=left; i<right; i++){
            if(arr[i] <= arr[right]){
                //swap
                int temp = arr[i];
                arr[i] = arr[p];
                arr[p] = temp;

                p++;
            }
        }

        //at the end swap pivot
        int temp = arr[right];
        arr[right] = arr[p];
        arr[p] = temp;

        if(p == k){
            return arr[p];
        } else if (p < k) {
            return quickSelect(arr, p+1, right, k);
        }else {
            return quickSelect(arr, left, p-1, k);
        }
    }
}
