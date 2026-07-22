package slidingWindow;

import java.util.*;

public class LongestSubstrWithKDistinctChar {

    //https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/

    //Longest Substring with At Most K Distinct Characters

    public int lengthOfLongestSubstring(String s, int k)
    {
        //input validation
        if (s == null || s.isEmpty() || k == 0) {
            return 0;
        }

        Map<Character, Integer> freqMap = new HashMap<>();
        int windowStart = 0;
        int maxLen = 0;

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            char ch = s.charAt(windowEnd);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            //keep on shrinking window till we have at most k distinct chars
            while(freqMap.size() > k){

                char lch = s.charAt(windowStart++);
                freqMap.put(lch, freqMap.get(lch) - 1);

                if(freqMap.get(lch) == 0){
                    freqMap.remove(lch);
                }
            }

            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
        }

        return maxLen;
    }
}
