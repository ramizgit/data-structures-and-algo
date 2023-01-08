package array;

public class JumpGame {
    public static void main(String[] args)
    {
        System.out.println(canJump(new int[]{2,3,1,1,4})); //true
        System.out.println(canJump(new int[]{3,2,1,0,4})); //false
        System.out.println(canJump(new int[]{3,2,2,0,4})); //true
        System.out.println(canJump(new int[]{3,0,0,0,4})); //false
        System.out.println(canJump(new int[]{4,0,0,0,4})); //true
        System.out.println(canJump(new int[]{5,0,0,0,4})); //true
    }

    private static boolean canJump(int[] nums)
    {
        int current = nums.length-1;

        for(int i=nums.length-2; i>=0; i--){
            if(i + nums[i] >= current){
                current = i;
            }
        }

        return current == 0;
    }
}
