package slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStrWithoutRepeatingCh {

    public static void main(String[] args)
    {
        System.out.println(lengthOfLongestSubstring("abcda")); //4
        System.out.println(lengthOfLongestSubstring("abcabcbb")); //3
        System.out.println(lengthOfLongestSubstring("pwwkewrtw")); //5
        System.out.println(lengthOfLongestSubstring("bbbbb")); //1
    }

    private static int lengthOfLongestSubstring(String s)
    {
        if(s == null || s.isEmpty()){
            return 0;
        }

        int left = 0;
        int right = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();

        while(right < s.length()){
            char ch = s.charAt(right);

            while(set.contains(ch)){
                set.remove(s.charAt(left));
                left++;
            }

            set.add(ch);

            maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }

        return maxLen;
    }
}
