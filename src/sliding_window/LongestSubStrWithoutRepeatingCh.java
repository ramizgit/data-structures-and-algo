package sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStrWithoutRepeatingCh {

    //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

    //Approach 1 : Using Set - Expand the window and shrink it until all characters in the window are unique.
    //Time : O(n)
    //Space : O(n)
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

    //Approach 2 : Using Map - Store each character's last seen index to jump the window start past duplicates.
    //Time : O(n)
    //Space : O(n)
    private static int lengthOfLongestSubstringViaMap(String s)
    {
        //input validation
        if(s == null || s.isEmpty()){
            return 0;
        }

        //sliding window
        int windowStart = 0;
        int maxLen = 0;
        Map<Character, Integer> lastSeen = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            char current = s.charAt(windowEnd);

            if(lastSeen.containsKey(current)){
                windowStart = Math.max(windowStart, lastSeen.get(current) + 1); //prevent moving the left pointer backward when the previous occurrence is already outside the window.
            }

            lastSeen.put(current, windowEnd);

            maxLen = Math.max(maxLen, windowEnd - windowStart + 1); //capture max window length
        }

        return maxLen;
    }
}
