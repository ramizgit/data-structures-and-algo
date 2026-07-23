package sliding_window;

import java.util.*;

public class FindAllAnagramsInAString {

    //https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

    public List<Integer> findAnagrams(String s, String p)
    {
        //input validation
        if (s == null || p == null) {
            return Collections.emptyList();
        }

        if(p.length() > s.length()){
            return Collections.emptyList();
        }

        //build freq of p
        int[] need = new int[26]; //since only lowercase english letters

        for(char ch : p.toCharArray()){
            need[ch - 'a']++;
        }

        int windowStart = 0;
        int[] window = new int[26];

        List<Integer> result = new ArrayList<>();

        //slide through s and find anagrams
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++){

            //expand window
            char ch = s.charAt(windowEnd);
            window[ch - 'a']++;

            //proceed only if window of size p is built
            if(windowEnd - windowStart + 1 < p.length()){
                continue;
            }

            //compare need vs window
            if(isAnagram(need, window)){
                result.add(windowStart);
            }

            //shrink window
            char lch = s.charAt(windowStart++);
            window[lch - 'a']--;
        }

        return result;
    }

    private boolean isAnagram(int[] need, int[] window)
    {
        for(int i=0; i<26; i++){
            if(need[i] != window[i]){
                return false;
            }
        }

        return true;
    }
}
