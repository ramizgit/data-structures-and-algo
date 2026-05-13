package longestIncreasingSubseqVariants;

import java.util.*;

/*
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
One envelope can fit into another if and only if both the width and height of one envelope are greater than the other
envelope's width and height.
Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 */

/*
Trick : sort one dimension so the other dimension can be processed independently
 */

public class RussianDollEnvelopes {

   //https://leetcode.com/problems/russian-doll-envelopes/description/

    private static int maxEnvelopes(int[][] envelopes)
    {
        //sort by ascending width, if tie, then sort by descending height
        Arrays.sort(envelopes, (a,b) -> {
           if(a[0] == b[0]){
               return b[1] - a[1]; //desc height for equals widths
           }else{
               return a[0] - b[0]; //asc width
           }
        });

        int n = envelopes.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1); //base case
        int maxLen = 1;

        for(int i=0; i<n; i++){
            int currHeight = envelopes[i][1];
            for(int j=0; j<i; j++){
                int prevHeight = envelopes[j][1];
                if(prevHeight < currHeight){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}
