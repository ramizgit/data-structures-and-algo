package consistenthashing.practice;

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums)
    {
        int total = 0;

        for(int num : nums){
            total += num;
        }

        if(total % 2 != 0){
            return false;
        }

        int target = total / 2;
        int n = nums.length;

        //dp
        boolean[] dp = new boolean[target+1];
        dp[0] = true; //base case

        for(int i=0; i<n; i++){
            for(int t=target; t>=0; t--){
                dp[t] = dp[t] //pick
                        || dp[t-i]; //dont pick
            }
        }

        return dp[target];
    }


    //Time : O(2^n)
    public boolean canPartitionBruteForce(int[] nums)
    {
        int total = 0;

        for(int num : nums){
            total += num;
        }

        if(total % 2 != 0){
            return false;
        }

        return dfs(nums, 0, 0, total / 2);
    }

    private boolean dfs(int[] nums, int index, int curr, int target)
    {
        if(curr == target){
            return true;
        }

        if(index == nums.length){
            return false;
        }

        return dfs(nums, index + 1, curr + nums[index], target) //pick
                || dfs(nums, index + 1, curr, target); //dont pick

    }
}
