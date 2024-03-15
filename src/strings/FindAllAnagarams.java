package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagarams {

    public static void main(String[] args)
    {
        //Input: s = "cbaebabacd", p = "abc"

        System.out.println(findAllAnagarams("cbaebabacd", "abc")); //[0,6]
        System.out.println(findAllAnagarams("abab", "ab")); //[0,1,2]
    }

    private static List<Integer> findAllAnagarams(String s, String p)
    {
        int numOfAnagram = 0;
        List<Integer> result = new ArrayList<>();
        if(s.length() < p.length()){
            return new ArrayList<>();
        }

        Map<Character, Integer> pCharFreq = new HashMap<>();
        Map<Character, Integer> cCharFreq = new HashMap<>();

        for(char ch : p.toCharArray()){
            pCharFreq.put(ch, pCharFreq.getOrDefault(ch, 0)+1);
        }

        int left = 0;
        int right = p.length()-1;

        for(int i=left; i<=right; i++){
            cCharFreq.put(s.charAt(i), cCharFreq.getOrDefault(s.charAt(i), 0)+1);
        }

        //check if anagram or not


        while(left < s.length() && right < s.length()){
            if(isAnagram(pCharFreq, cCharFreq)){
                numOfAnagram++;
                result.add(left);
                //add to result
            }

            //move left
            cCharFreq.put(s.charAt(left), cCharFreq.get(s.charAt(left))-1);
            if(cCharFreq.get(s.charAt(left)) == 0){
                cCharFreq.remove(s.charAt(left)); //remove char if freq is zero
            }
            left++;

            //move right
            right++;
            if(right < s.length()){
                cCharFreq.put(s.charAt(right), cCharFreq.getOrDefault(s.charAt(right), 0)+1);
            }
        }

        System.out.println("num of anagram = "+numOfAnagram);

        return result;
    }

    private static boolean isAnagram(Map<Character, Integer> pCharFreq, Map<Character, Integer> cCharFreq)
    {
        if(pCharFreq.keySet().size() != cCharFreq.keySet().size()){
            return false;
        }

        for(char ch : pCharFreq.keySet()){
            if(pCharFreq.get(ch) != cCharFreq.get(ch)){
                return false;
            }
        }

        return true;
    }
}
