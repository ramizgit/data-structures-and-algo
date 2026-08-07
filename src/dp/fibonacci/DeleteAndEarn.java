package dp.fibonacci;

public class DeleteAndEarn {

    //https://leetcode.com/problems/delete-and-earn/description/

    public int deleteAndEarn(int[] nums)
    {
        /*
        Hint:- Convert the problem into House Robber by aggregating all occurrences of the same value into a single bucket.
         */

        //input validation
        if(nums == null || nums.length == 0){
            return 0;
        }

        int maxValue = 0;

        for(int num : nums){
            maxValue = Math.max(maxValue, num);
        }

        int[] earn = new int[maxValue+1];

        for(int num : nums){
            earn[num] += num; //accumulate total points for each value
        }

        int[] dp = new int[maxValue+1]; //dp[i] = maximum points that can be earned considering values from 0 to i

        dp[0] = 0; //base case
        dp[1] = Math.max(earn[0], earn[1]);

        for(int i=2; i<=maxValue; i++){

            dp[i] = Math.max(
                    earn[i] + dp[i-2], //pick current value
                    dp[i-1] //skip current value
            );
        }

        return dp[maxValue];
    }
}
