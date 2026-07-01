package consistenthashing.dp.knapsack;

public class PartitionEqualSubsetSum2D {

    //https://leetcode.com/problems/partition-equal-subset-sum/description/
    
    //using 1-D array, 0/1 knapsack, using backward loop
    public static boolean canPartition1Ddp(int[] nums)
    {
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        if (total % 2 != 0) {
            return false;
        }

        int target = total / 2;

        boolean[] dp = new boolean[target+1]; // dp[t] = true if some subset forms sum t
        dp[0] = true; //base case, 0 sum is always possible by picking no element

        for(int num : nums){ //processes each number one by one and decide can this number help us form new sums?

            //check all sums we care about
            /*
            Can num help me form sum target?
            Can num help me form sum target-1?
            Can num help me form sum target-2?
            ...
             */
            for(int t=target; t>=num; t--){ //backward loop to avoid reusing same number since 0/1 knspsack, it ensures each number is used at most once

                dp[t] = dp[t] //dont pick as sum t already achievable
                        || dp[t - num]; //pick, achieve t by adding current number
            }
        }

        return dp[target];
    }

    /*
    simple example how backward loop avoids reuse
    nums = [5]
    target = 10

    if we use forward loop,
    dp[0] = true
    dp[5] = dp[5] || dp[0] = true
    dp[10] = dp[10] || dp[5] = true !bingo, we used 5 twice here
     */

    //using 2-D, but not preferable, follow above 1-D solution only
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int n : nums) {
            totalSum += n;
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        int n = nums.length;

        // dp[i][s] = can we form sum s using first i elements
        boolean[][] dp = new boolean[n + 1][target + 1];

        // base case
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int t = 0; t <= target; t++) {
                if(nums[i-1] <= t){
                    dp[i][t] = dp[i-1][t] //dont pick
                            || dp[i - 1][t - nums[i - 1]]; //pick
                }else{
                    dp[i][t] = dp[i - 1][t]; //dont pick
                }
            }
        }

        return dp[n][target];
    }
    
    // Optional test
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums)); // true
    }
}

