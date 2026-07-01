package consistenthashing.dp.lineardp;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {

    //https://leetcode.com/problems/word-break/description/

    public boolean wordBreak(String str, String[] wordDict)
    {
        Set<String> set = new HashSet<>();
        int maxLen = 0;

        for(String word : wordDict){
            set.add(word);
            maxLen = Math.max(maxLen, word.length());
        }

        int n = str.length();

        boolean[] dp = new boolean[n+1]; //dp[i] = true means the first i characters be segmented
        dp[0] = true; //base case for empty string

        for(int i=1; i<=n; i++){

            //try every possible previous cut
            for (int j = Math.max(0, i - maxLen); j < i; j++) {

                //current partition = str[j...i-1]
                if (set.contains(str.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
