package consistenthashing.prefixSum;

public class SpecialArrayII {

    //https://leetcode.com/problems/special-array-ii/description/

    /*
    An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
    You are given an array of integer nums and a 2D integer matrix queries,
    where for queries[i] = [fromi, toi] your task is to check that subarray nums[fromi..toi] is special or not.
    Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.
     */

    public boolean[] isArraySpecial(int[] nums, int[][] queries)
    {
        int n = nums.length;

        //build bad array
        int[] bad = new int[n]; //bad[i] = 1 if nums[i-1] and nums[i] have same parity

        for(int i=1; i<n; i++){
            if(nums[i] % 2 == nums[i-1] % 2){
                bad[i] = 1;
            }
        }

        //prefix sum
        int[] prefixSum = new int[n];
        prefixSum[0] = bad[0];
        for(int i=1; i<n; i++){
            prefixSum[i] = bad[i] + prefixSum[i-1];
        }

        //iterate queries and collect results
        boolean[] answer = new boolean[queries.length];

        for(int i=0; i<queries.length; i++){
            int l = queries[i][0];
            int r = queries[i][1];

            int rangeSum = prefixSum[r] - prefixSum[l];

            answer[i] = rangeSum == 0;
        }

        return answer;
    }
}
