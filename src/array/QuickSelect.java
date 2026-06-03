package array;

public class QuickSelect {

    public int findKthLargest(int[] nums, int k)
    {
        int targetIndex = nums.length - k;

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){

            int pivotIndex = partition(nums, left, right);

            if(pivotIndex == targetIndex){
                return nums[pivotIndex];
            }else if(pivotIndex < targetIndex){
                left = pivotIndex + 1;
            }else{
                right = pivotIndex - 1;
            }
        }

        return -1;
    }

    private int partition(int[] nums, int left, int right)
    {
        int pivot = nums[right];

        int i = left;

        for(int j = left; j < right; j++){
            if(nums[j] <= pivot){
                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, i, right);

        return i;
    }

    private void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
