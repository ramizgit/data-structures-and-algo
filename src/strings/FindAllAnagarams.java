package google;

import java.util.*;

public class FindAllAnagrams {

    public static void main(String[] args) {
        System.out.println(findAllAnagrams("cbaebabacd", "abc")); // [0, 6]
        System.out.println(findAllAnagrams("abab", "ab"));        // [0, 1, 2]
    }

    public static List<Integer> findAllAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) return result;

        int[] freq = new int[26];

        // Count chars in p
        for (char c : p.toCharArray()) {
            freq[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int remaining = p.length();  // chars we still need to match

        while (right < s.length()) {

            // Include s[right]
            char rc = s.charAt(right);
            if (freq[rc - 'a'] > 0) {
                remaining--;
            }
            freq[rc - 'a']--;

            // When window size equals p.length()
            if (remaining == 0) {
                result.add(left);
            }

            // Shrink window to size p.length()
            if (right - left == p.length()) {
                char lc = s.charAt(left);

                if (freq[lc - 'a'] >= 0) {
                    remaining++;
                }

                freq[lc - 'a']++;
                left++;
            }

            right++;
        }

        return result;
    }
}
