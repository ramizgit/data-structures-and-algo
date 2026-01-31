package practice;

import java.util.*;

public class CustomSortString {

    //https://leetcode.com/problems/custom-sort-string/description/
    public static void main(String[] args)
    {
        System.out.println(customSortString("cba", "abcd"));
        System.out.println(customSortString("bcafg", "abcd"));
    }

    private static String customSortString(String order, String s)
    {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char c : s.toCharArray()){
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for(char ch : order.toCharArray()){
            int freq = freqMap.getOrDefault(ch, 0);
            for(int i=0; i<freq; i++){
                sb.append(ch);
            }

            //remove from map, so that we can easily append left over chars at the end
            freqMap.remove(ch);
        }

        //append left overs
        for(char ch : freqMap.keySet()){
            int freq = freqMap.get(ch);
            for(int i=0; i<freq; i++){
                sb.append(ch);
            }
        }

        return sb.toString();
    }

}
