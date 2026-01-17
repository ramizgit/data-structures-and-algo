package array;

public class JumpGameII {
    public static void main(String[] args)
    {
        System.out.println(minJump(new int[]{2,3,1,1,4})); //2
        System.out.println(minJump(new int[]{2,3,0,1,4})); //2
        System.out.println(minJump(new int[]{1,1,1,1,4})); //4
        System.out.println(minJump(new int[]{1,2,1,1,4})); //3

    }

    private static int minJump(int[] arr)
    {
        int count=0;
        int left=0;
        int right=0;

        while (right < arr.length - 1){
            int farthest=0;
            while (left <= right){
                farthest = Math.max(farthest, arr[left] + left);
                left++;
            }

            left = right+1;
            right = farthest;
            count++;
        }

        return count;
    }
}
