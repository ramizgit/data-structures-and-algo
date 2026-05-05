package google;

/*
Problem - An arithmetic sequence is a list of numbers with a definite pattern. If you take any number in the sequence
then subtract it from the previous one, the difference is always a constant.
A good arithmetic sequence is an arithmetic sequence with a common difference of either 1 or -1.
For example, [4, 5, 6] is a good arithmetic sequence. So is [6, 5, 4], [10, 9], or [-3, -2, -1]. But, [1, 2, 1] (no common difference)
or [3, 7] (common difference is 4) is NOT.
Implied, any sequence that has only one element is a good arithmetic sequence.

For example, [4] is a good arithmetic sequence.
Given an integer array nums, return the sum of the sums of each subarray that is a good arithmetic sequence.

Example:

Given nums = [7, 4, 5, 6, 5]. Each of the following subarrays is a good arithmetic sequence:

[7], [4], [5], [6], [5],
[4, 5], [5, 6], [6, 5],
[4, 5, 6]
The sums of these subarrays are:

7, 4, 5, 6, 5,
4 + 5 = 9, 5 + 6 = 11, 6 + 5 = 11,
4 + 5 + 6 = 15
Thus, the answer is the sum of all the sums above, which is:

7 + 4 + 5 + 6 + 5 + 9 + 11 + 11 + 15 = 73.
 */

public class SumOfAllGoodSubarrays {

    public long sumOfGoodSubarrays(int[] nums) {

        int n = nums.length;
        long total = nums[0];
        long sumEndingHere = nums[0];
        int len = 1;

        for (int i = 1; i < n; i++) {

            int diff = nums[i] - nums[i - 1];

            if (Math.abs(diff) == 1) {
                if (i > 1 && diff == nums[i - 1] - nums[i - 2]) {
                    len++;
                    sumEndingHere = sumEndingHere + len * nums[i];
                } else {
                    len = 2;
                    sumEndingHere = nums[i] + (nums[i - 1] + nums[i]);
                }
            } else {
                len = 1;
                sumEndingHere = nums[i];
            }

            total += sumEndingHere;
        }

        return total;
    }
}
