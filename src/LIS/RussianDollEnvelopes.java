package longestIncreasingSubseqVariants;

import java.util.*;

public class RussianDollEnvelopes {

    public static void main(String[] args)
    {
        int[][] envelopes = {
                {5,4},
                {6,4},
                {6,7},
                {2,3}
        };

        System.out.println(maxEnvelopes(envelopes)); // Expected: 3
    }

    private static int maxEnvelopes(int[][] envelopes)
    {
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
            for(int j=0; j<i; j++){
                if(envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}
