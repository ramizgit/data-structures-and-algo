package dp;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {
    //https://leetcode.com/problems/word-break/description/
    public static void main(String[] args)
    {
        System.out.println(wordBreak("leetcode", new String[]{"leet","code"}));
    }

    private static boolean wordBreak(String str, String[] wordDict)
    {
        Set<String> set = new HashSet();
        int maxLen = 0;

        for(String word : wordDict){
            set.add(word);
            maxLen = Math.max(maxLen, word.length());
        }

        int n = str.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int i=1; i<=n; i++){
            for(int j=i-1; j>= Math.max(0, i - maxLen); j--){
                if(set.contains(str.substring(j, i)) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
