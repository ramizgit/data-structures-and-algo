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
        int left = 0;
        int right = 0;
        int len = 0;

        Set<Character> set = new HashSet<>();

        while (right < s.length()){

            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
                len = Math.max(len, right - left);
            }else {
                while (left <= right){
                    if(s.charAt(left) == s.charAt(right)){
                        set.remove(s.charAt(left));
                        left++;
                        break;
                    }
                    left++;
                }
            }
        }

        return len;
    }
}
