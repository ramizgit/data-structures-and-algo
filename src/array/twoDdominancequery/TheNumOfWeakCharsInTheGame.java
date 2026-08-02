package array.twoDdominancequery;

import java.util.*;

public class TheNumOfWeakCharsInTheGame {

    //https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/description/

    public int numberOfWeakCharacters(int[][] properties)
    {
        //input validation
        if(properties == null || properties.length == 0){
            return 0;
        }

        int n = properties.length;

        //sort by attack ascending; for equal attack, sort defense descending
        Arrays.sort( properties, (a, b) -> {

            if(a[0] == b[0]){
                return Integer.compare(b[1], a[1]); //desc defence for equal attack
            }

            return Integer.compare(a[0], b[0]); //asc attack
        });

        int weakCharacters = 0;
        int maxDefenceSeen = properties[n-1][1];

        for(int i=n-2; i>=0; i--){

            int currDefence = properties[i][1];

            if(currDefence < maxDefenceSeen){
                weakCharacters++;
            }

            maxDefenceSeen = Math.max(maxDefenceSeen, currDefence);
        }

        return weakCharacters;
    }
}
