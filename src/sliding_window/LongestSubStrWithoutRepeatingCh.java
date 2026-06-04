package slidingwindow;

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
        int left = 0;
        int right = 0;
        int maxLen = 0;
        Set<Character> windowSet = new HashSet<>();

        while(right < s.length()){

            char current = s.charAt(right);

            while(windowSet.contains(current)){
                //shrink window
                windowSet.remove(s.charAt(left));
                left++;
            }

            windowSet.add(current); //expand window

            maxLen = Math.max(maxLen, right - left + 1); //capture max window length

            right++;
        }

        return maxLen;
    }
}
