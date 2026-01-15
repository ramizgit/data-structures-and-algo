package dp;

public class PartitionEqualSubsetSum2D {

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

    //todo:write usign 1-D array, ints 0/1 knapsack, using backward loop
    public static boolean canPartition1Ddp(int[] nums) {
        int totalSum = 0;
        for (int n : nums) {
            totalSum += n;
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;

        boolean[] dp = new boolean[target+1]; //the DP array must be indexed by sum (target), not by number of elements
        dp[0] = true; //base case

        for(int i=1; i<=nums.length; i++){
            //backward loop since 0/1 knspsack
            for(int t=target; t>=nums[i-1]; t--){
                dp[t] |= dp[t - nums[i-1]];
            }
        }

        return dp[target];
    }

    // Optional test
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums)); // true
    }
}

