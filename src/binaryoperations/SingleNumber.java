package binary;

public class SingleNumber {
    //https://leetcode.com/problems/single-number/description/
    /*
    There could be multiple ways:-
    1. brute force : use set to add elements, if already added, remove it, finally set will have the answer. but O(n) space
    2. sort and use two pointers, but non-linear (0(nlogn)
    3. XOR - recommended - linear and no extra space
     */

    public static void main(String[] args)
    {
        System.out.println(singleNumber(new int[]{2,2,1}));
        System.out.println(singleNumber(new int[]{4,1,2,1,2}));
        System.out.println(singleNumber(new int[]{1}));
    }

    private static int singleNumber(int[] nums)
    {
        int result = 0;
        for(int num : nums){
            result ^= num;
        }

        return result;
    }
}
