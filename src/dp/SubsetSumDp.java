package dp;

public class SubsetSumDp {

    public static void main(String[] args)
    {
        System.out.println(subsetSum(new int[]{1,2,3,4,5}, 11));
        System.out.println(subsetSumNoRepetation(new int[]{1,2,3,4,5}, 11));
    }

    //-------repetation allowd, unbounded knapsack, similar to coin change
    /*
    nums = [2]
    target = 4

    Initially:
    dp = [T F F F F]

    Process 2:
    t = 2 -> dp[2] = dp[0] = T
    t = 3 -> dp[3] = dp[1] = F
    t = 4 -> dp[4] = dp[2] = T   // dp[2] was updated earlier in this iteration
    Result: dp = [T F T F T]
     */
    private static boolean subsetSum(int[] nums, int target)
    {
        boolean[] dp = new boolean[target+1];
        dp[0] = true; //base case

        for (int n : nums) {
            for (int t = n; t <= target; t++) {
                dp[t] = dp[t] //dont pick
                        || dp[t - n]; //pick
            }
        }

        return dp[target];
    }

    //-------repetation not allowd, 0/1 knapsack
    /*
    Example:-

    nums = [2]
    target = 4

    Initially:
    dp = [T F F F F]

    Process 2:
    t = 4 -> dp[4] = dp[2] = F   // dp[2] hasn't been updated yet
    t = 3 -> dp[3] = dp[1] = F
    t = 2 -> dp[2] = dp[0] = T
    Result: dp = [T F T F F]
     */
    private static boolean subsetSumNoRepetation(int[] nums, int target)
    {
        boolean[] dp = new boolean[target+1]; // dp[t] = true if some subset forms sum t
        dp[0] = true; //base case, 0 sum is always possible by picking no element

        for(int n : nums){
            for(int t=target; t>=n; t--){ //backward loop to avoid reusing same number since 0/1 knspsack, it ensures each number is used at most once
                dp[t] = dp[t] //dont pick
                        || dp[t - n]; //pick
            }
        }

        return dp[target];
    }
}
