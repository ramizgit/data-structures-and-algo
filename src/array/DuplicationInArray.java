package array;

public class DuplicationInArray {
    //from harry he coding interview - page number 34
    //array contains n elements in the range [0..n-2]. there is *exactly one* num duplicated in the array. find the duplicate number.

    public static void main(String[] args)
    {
        System.out.println(findDuplicate(new int[]{0,2,1,3,2}));
    }

    /*
    multiples ways to sovle it
    1. using Set - Time : O(n), Space : O(n)
    2. Sort the array - Time : O(nlogn), Space : O(1)
    3. summation logic (below second impl)
    4. change sign in place - some issue in this, u cant chagne sign of 0
     */

    //change sign in place
    private static int findDuplicate(int[] nums)
    {
        int n = nums.length;
       int sum = 0;
       for(int num : nums){
           sum += num;
       }

       int expected = (n-2) * (n-1) / 2;

       return sum - expected;
    }
}
