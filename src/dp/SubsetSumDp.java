package dp;

public class SubsetSumDp {

    public static void main(String[] args)
    {
        System.out.println(subsetSum(new int[]{1,2,3,4,5}, 11));
        System.out.println(subsetSumNoRepetation(new int[]{1,2,3,4,5}, 11));
    }

    //-------repetation allowd, unbounded knapsack, similar to coin change
    private static boolean subsetSum(int[] nums, int target)
    {
        boolean[] dp = new boolean[target+1];
        dp[0] = true; //base case

        for(int t=1; t<=target; t++){
            for(int n : nums){
                if(t >= n){
                    dp[t] = dp[t] //dont pick
                            || dp[t - n]; //pick
                }
            }
        }

        return dp[target];
    }

    //-------repetation not allowd, 0/1 knapsack
    private static boolean subsetSumNoRepetation(int[] nums, int target)
    {
        boolean[] dp = new boolean[target+1];
        dp[0] = true; //base case

        for(int n : nums){
            for(int t=target; t>=n; t--){
                dp[t] = dp[t] //dont pick
                        || dp[t - n]; //pick
            }
        }

        return dp[target];
    }
}
