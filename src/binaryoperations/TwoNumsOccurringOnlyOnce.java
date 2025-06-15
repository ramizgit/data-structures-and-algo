package binary;

public class TwoNumsOccurringOnlyOnce {

    /*
    all nums except two occur twice in an array, find those two nums in 0(n) and 0(1) space
     */

    public static void main(String[] args)
    {
        findSingleOccurringNums(new int[]{2,4,3,6,3,2,5,5});
    }

    private static void findSingleOccurringNums(int[] nums)
    {
        //step1 : xor all elements
        int xor = 0;
        for(int num : nums){
            xor ^= num;
        }

        //step2 : find first 1 bit in the xor from right
        int idx = 0;
        while ((xor & 1) != 1){
            xor = xor >> 1;
            idx++;
        }

        //step3 : divide array into two
        int num1 = 0;
        int num2 = 0;

        for(int num : nums){
            if(isIthBitMatching(num, idx)){
                num1 ^= num;
            }else {
                num2 ^= num;
            }
        }

        System.out.println("num1 = "+num1+" num2="+num2);
    }

    private static boolean isIthBitMatching(int num, int shift)
    {
        num = num >> shift;
        return (num & 1) == 1;
    }
}
