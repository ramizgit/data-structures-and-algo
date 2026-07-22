package slidingWindow;

import java.util.*;

public class LongestRepeatingCharacterReplacement {

    //https://leetcode.com/problems/longest-repeating-character-replacement/description/

    public int characterReplacement(String s, int k)
    {
        if(s == null || s.isEmpty()){
            return 0;
        }

        int windowStart = 0;
        int maxLen = 0;
        int maxFreq = 0;
        //Map<Character, Integer> freq = new HashMap<>();
        int[] freq = new int[26]; //input string contains only uppercase English letters

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            char ch = s.charAt(windowEnd);
            freq[ch - 'A']++;
            maxFreq = Math.max(maxFreq, freq[ch - 'A']); //highest frequency of any single character in the current window

            //shrink window till have at most k char different from max freq char
            while( ((windowEnd - windowStart + 1) - maxFreq) > k){
                char lch = s.charAt(windowStart++);
                freq[lch - 'A']--;

                /*
                IMPORTANT:-
                Why don't you decrease maxFreq when shrinking the window?
                maxFreq is allowed to be stale. It only ever increases. A stale value may delay shrinking slightly,
                but it never causes us to miss the optimal answer, which keeps the algorithm linear.
                 */
            }

            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
        }

        return maxLen;
    }
}
