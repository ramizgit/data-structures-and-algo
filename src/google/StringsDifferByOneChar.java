package google;

import java.util.*;

public class StringsDifferByOneChar {

    //https://leetcode.com/problems/strings-differ-by-one-character/description/

    public boolean differByOne(String[] dict)
    {
        /*
        // Appraoch:-
        // Replace each character of every word with a wildcard ('*') one position at a time.
        // If two words differ by exactly one character at the same position,
        // they will generate the same wildcard pattern.
        // Store all generated patterns in a HashSet. If a pattern is seen again,
        // two words differ by exactly one character.
         */

        Set<String> set = new HashSet<>();

        for(String word : dict){

            for(int i=0; i<word.length(); i++){

                StringBuilder sb = new StringBuilder();
                sb.append(word.substring(0, i)).append("*").append(word.substring(i+1));

                String pattern = sb.toString();

                if(set.contains(pattern)){
                    return true;
                }else{
                    set.add(pattern);
                }
            }
        }

        return false;
    }
}
