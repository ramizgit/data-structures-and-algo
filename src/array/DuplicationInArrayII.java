package array;

public class DuplicationInArrayII {
    //from harry he coding interview - page number 35
    //array contains n elements in the range [0..n-1]. there are more than one num duplicated in the array. find any one of the duplicate numbers.

    public static void main(String[] args)
    {
        System.out.println(findDuplicate(new int[]{0,2,1,3,2}));
        System.out.println(findDuplicate(new int[]{0,1,2,3,3}));
    }

    private static int findDuplicate(int[] nums)
    {
        int n = nums.length;
        for(int i=0; i<n; i++){
            while(nums[i] != i){
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }

                //swap
                int sourceIdx = i;
                int targetIdx = nums[i];
                int temp = nums[sourceIdx];
                nums[sourceIdx] = nums[targetIdx];
                nums[targetIdx] = temp;
            }
        }
        return -1;
    }
}
