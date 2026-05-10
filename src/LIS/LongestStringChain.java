package longestIncreasingSubseqVariants;

import java.util.*;

public class LongestStringChain {

    //https://leetcode.com/problems/longest-string-chain/description/

    //Time : O(n² * L), where n is num of words, and L is avg word length
    public int longestStrChain(String[] words)
    {
        //sort by increasing length
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int n = words.length;

        //LIS DP logic
        int[] dp = new int[n];
        Arrays.fill(dp, 1); //each string word is of chain length 1

        int longest = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(isPredecessor(words[i], words[j])){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }

            longest = Math.max(longest, dp[i]);
        }

        return longest;

    }

    private boolean isPredecessor(String longer, String shorter)
    {
        if(longer.length() != shorter.length() + 1){
            return false;
        }

        int l = 0;
        int s = 0;

        while(l < longer.length() && s < shorter.length()){
            if(longer.charAt(l) == shorter.charAt(s)){
                l++;
                s++;
            }else{
                l++;
            }
        }

        return s == shorter.length();
    }
}
