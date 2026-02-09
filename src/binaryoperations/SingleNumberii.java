package binary;

public class SingleNumberii {

    //https://leetcode.com/problems/single-number-ii/description/
    /*
    Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
    You must implement a solution with a linear runtime complexity and use only constant extra space.
     */

    public int singleNumber(int[] nums)
    {
        int result = 0;

        for(int i=0; i<32; i++){
            int count = 0;

            for(int num : nums){
                num = num >> i;
                int last = (num & 1);
                count += last;
            }

            if(count % 3 != 0){
                result = result | (1 << i);

            }
        }

        return result;
    }
}
