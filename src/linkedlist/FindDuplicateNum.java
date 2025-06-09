package linkedlist;

public class FindDuplicateNum {

    //https://leetcode.com/problems/find-the-duplicate-number/description/
    public static void main(String[] args)
    {
        System.out.println(findDuplicate(new int[]{1,3,4,2,2})); //2
        System.out.println(findDuplicate(new int[]{3,1,3,4,2})); //3
        System.out.println(findDuplicate(new int[]{3,3,3,3,3})); //3
    }

    private static int findDuplicate(int[] nums)
    {
        int slow = 0;
        int fast = 0;

        while (true){
            slow = nums[slow];
            fast = nums[nums[fast]];

            if(slow == fast){
                break;
            }
        }

        int slow2 = 0;

        while (true){
            slow = nums[slow];
            slow2 = nums[slow2];

            if(slow == slow2){
                return slow;
            }
        }
    }
}
