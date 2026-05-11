package google;

import java.util.*;

public class StringsDifferByOneChar {

    //https://leetcode.com/problems/strings-differ-by-one-character/description/

    public boolean differByOne(String[] dict)
    {
        Set<String> set = new HashSet<>();

        for(String word : dict){
            for(int i=0; i<word.length(); i++){
                StringBuilder sb = new StringBuilder();
                sb.append(word.substring(0, i)).
                        append("*").
                        append(word.substring(i+1));

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
