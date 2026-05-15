package twoPointers;

import java.util.*;

public class ValidTriangleNumber {

    // https://leetcode.com/problems/valid-triangle-number/

    public int triangleNumber(int[] nums)
    {
        /*
        Approach:
        Sort array.
        Start frm right, fix largest side nums[i].
        Use two pointers to count pairs such that:
        nums[left] + nums[right] > nums[i]
        */

        Arrays.sort(nums);

        int n = nums.length;
        int count = 0;

        for(int i = n - 1; i >= 2; i--) { //because triangle needs 3 elements, hence i>=2

            int left = 0;
            int right = i - 1;

            while(left < right) {

                if(nums[left] + nums[right] > nums[i]) { //a+b>c

                     /*
                Valid triangle found.
                Since array is sorted: nums[left+1] >= nums[left]
                therefore ALL pairs between: [left ... right-1] with current right will also satisfy:
                nums[x] + nums[right] > nums[i]
                Hence add all at once.

                e.g.,
                0 1 2 3 4 5
                c = 5
                a = 2
                b = 4

                right - left = 4 - 2 = 2
                */
                    count += right - left; //valid triangle found
                    right--; //Move leftwards to try a smaller second side, because all pairs for current right are already counted.

                } else {
                     /*
                Sum too small: nums[left] + nums[right] <= nums[i]
                Need a larger value to make triangle valid.
                Since array sorted, increase left pointer.
                */
                    left++;
                }
            }
        }

        return count;
    }
}
