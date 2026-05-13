package arrays;

public class NextPermutation {

    //https://leetcode.com/problems/next-permutation/description/

    public void nextPermutation(int[] nums)
    {
        if(nums == null || nums.length == 0){
            return;
        }

        int n = nums.length;

        //step1 : find pivot
        int pivot = -1;
        //scan from right to left, find pivot where nums[i] < nums[i+1]
        for(int i=n-2; i>=0; i--){
            if(nums[i] < nums[i+1]){
                pivot = i;
                break;
            }
        }

        //check if array is all desc order
        if(pivot == -1){
            //reverse whole array
            reverse(nums, 0, n-1);
            return;
        }

        //step2 : swap with next greater element from right
        for(int i=n-1; i>pivot; i--){
            if(nums[i] > nums[pivot]){
                swap(nums, i, pivot);
                break;
            }
        }

        //step3 : reverse
        reverse(nums, pivot+1, n-1);
    }

    private void reverse(int[] nums, int start, int end)
    {
        while(start <= end){
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j)
    {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
