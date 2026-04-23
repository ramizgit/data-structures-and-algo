package intervals;

import java.util.*;

public class MaximumLengthOfPairChain {

    //https://leetcode.com/problems/maximum-length-of-pair-chain/

    public int findLongestChain(int[][] pairs)
    {
        /*
        Greedy rule : Always pick the interval with the earliest finishing time. This leaves maximum room for future intervals.
         */
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]); //sort asc by end time

        int chainLen = 1;
        int prevEnd = pairs[0][1];

        for(int i = 1; i<pairs.length; i++){
            if(pairs[i][0] > prevEnd){
                chainLen++;
                prevEnd = pairs[i][1]; // update to chosen interval
            }
        }

        return chainLen;
    }

    //LIS-style version (for learning)
    public int findLongestChainLIS(int[][] pairs)
    {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
