package sliding_window;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStrWithoutRepeatingCh {

    //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

    private static int lengthOfLongestSubstring(String s)
    {
        //input validation
        if(s == null || s.isEmpty()){
            return 0;
        }

        //sliding window
        int windowStart = 0;
        int maxLen = 0;
        Set<Character> window = new HashSet<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            char current = s.charAt(windowEnd);

            //shrink window till we don't have any repeating char
            while(window.contains(current)){
                window.remove(s.charAt(windowStart++));
            }

            window.add(current); //expand window

            maxLen = Math.max(maxLen, windowEnd - windowStart + 1); //capture max window length
        }

        return maxLen;
    }
}
