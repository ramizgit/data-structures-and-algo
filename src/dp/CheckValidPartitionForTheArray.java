package consistenthashing.dp;

public class CheckValidPartitionForTheArray {

    //https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/description/

    public boolean validPartition(int[] nums)
    {
        // input validation
        if (nums == null || nums.length < 2) {
            return false;
        }

        int n = nums.length;

        boolean[] dp = new boolean[n + 1]; // dp[i] = true means the first i elements can be partitioned validly
        dp[0] = true; // base case: empty prefix

        for (int i = 2; i <= n; i++) {

            /*
            "We check all possible choices, and if any choice forms a valid last partition and the remaining prefix has already been validly partitioned, then dp[i] = true."

            answer =
            choice1
            OR
            choice2
            OR
            choice3
             */

            // Case 1: Last two elements are equal
            if (nums[i - 2] == nums[i - 1]) {
                dp[i] |= dp[i - 2];
            }

            // Case 2: Last three elements are equal
            if (i >= 3 && nums[i - 3] == nums[i - 2] && nums[i - 2] == nums[i - 1]) {
                dp[i] |= dp[i - 3];
            }

            // Case 3: Last three elements are consecutive
            if (i >= 3 && nums[i - 2] == nums[i - 3] + 1 && nums[i - 1] == nums[i - 2] + 1) {
                dp[i] |= dp[i - 3];
            }
        }

        return dp[n];
    }
}
