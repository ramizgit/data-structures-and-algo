package binary;

public class MissingNumber {
    //https://leetcode.com/problems/missing-number/description/
    public static void main(String[] args)
    {
        System.out.println(missingNumber(new int[]{3,0,1})); //2
        System.out.println(missingNumber(new int[]{0,1})); //2
        System.out.println(missingNumber(new int[]{9,6,4,2,3,5,7,0,1})); //8

        System.out.println("=================XOR=================");

        System.out.println(missingNumberXor(new int[]{3,0,1})); //2
        System.out.println(missingNumberXor(new int[]{0,1})); //2
        System.out.println(missingNumberXor(new int[]{9,6,4,2,3,5,7,0,1})); //8
    }

    private static int missingNumber(int[] nums)
    {
        int n = nums.length;

        int expectedSum = n * (n+1) / 2;

        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        return expectedSum - sum;
    }

    private static int missingNumberXor(int[] nums)
    {
        int n = nums.length;

        int expectedXor = 0;
        for(int i=0; i<=n; i++){
            expectedXor ^= i;
        }

        int xor = 0;
        for(int num : nums){
            xor ^= num;
        }

        return expectedXor ^ xor;
    }
}
