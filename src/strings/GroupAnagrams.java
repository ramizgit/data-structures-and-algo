package mock;

import java.util.*;

public class GroupAnagrams {

    //https://leetcode.com/problems/group-anagrams/description/

    /*USE FREQ ARR WITHOUT SORTING*/
    /*
    Time complexity
    N = number of strings
    K = average string length
    Building frequency array: O(k)
    Total : O(N × K)
     */
    public List<List<String>> groupAnagrams(String[] strs)
    {
        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs){
            int[] freq = new int[26];
            for(char ch : str.toCharArray()){
                freq[ch - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for(int f : freq){
                sb.append(f).append("#");
            }

            String key = sb.toString();
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    //using sorting approach, but with higher time complexity : O(N × K log K)
    public List<List<String>> groupAnagrams2(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
