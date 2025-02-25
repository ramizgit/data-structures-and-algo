package meta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerifyAlienDict {
    public static void main(String[] args)
    {
        System.out.println(isAlienSorted(Arrays.asList("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz")); //true
        System.out.println(isAlienSorted(Arrays.asList("word", "world", "row"), "worldabcefghijkmnpqstuvxyz"));//false
    }

    private static boolean isAlienSorted(List<String> words, String order)
    {
        //edge case
        if(words == null || words.isEmpty() || words.size() == 1){
            return true;
        }

        //alphebet order weight
        Map<Character, Integer> weight = new HashMap<>();
        int w = 1;
        for(char ch : order.toCharArray()){
            weight.put(ch, w++);
        }

        for(int i=0; i<words.size()-1; i++){
            String word1 = words.get(i);
            String word2 = words.get(i+1);

            //compare the two words
            for(int j=0; j<word1.length(); j++){
                //if we reach end of word2 before word1, return false
                if(j == word2.length()){
                    return false;
                }

                //compare char by char
                if(word1.charAt(j) != word2.charAt(j)){
                    if(weight.get(word1.charAt(j)) > weight.get(word2.charAt(j))){
                        return false;
                    }else {
                        break;
                    }
                }
            }
        }
        return true;
    }
}
