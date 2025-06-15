package binary;

public class NumOfOneBits {
    //https://leetcode.com/problems/number-of-1-bits/description/
    public static void main(String[] args)
    {
        System.out.println(hammingWeight(11)); //3
        System.out.println(hammingWeight(128)); //1
        System.out.println(hammingWeight(2147483645)); //30

        System.out.println("==========Right Shift");

        System.out.println(usingRightShift(11)); //3
        System.out.println(usingRightShift(128)); //1
        System.out.println(usingRightShift(2147483645)); //30
    }

    private static int hammingWeight(int n)
    {
        int count = 0;

        while (n != 0){
            n = n & (n-1);
            count++;
        }

        return count;
    }

    private static int usingRightShift(int n)
    {
        int count = 0;
        while (n != 0){
            count += (n&1);
            n = n >>> 1;
        }

        return count;
    }
}
