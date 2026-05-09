package google;

import java.util.*;

//https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/

/*
Trick : sort one dimension so the other dimension can be processed independently
 */

public class NumberOfWeakCharactersInTheGame {

    public int numberOfWeakCharacters(int[][] properties)
    {
        // sort by attack descending
        // if equal attack, sort defense ascending
        Arrays.sort(properties, (a, b) -> {
           if(a[0] == b[0]){
               return a[1] - b[1]; //asc order of defense if equal attack
           }

           return b[0] - a[0]; //desc order of attack
        });

        int weakCharacters  = 0;
        int maxDefense = properties[0][1]; //starting point

        for(int i=1; i<properties.length; i++){

            int currDefense = properties[i][1];
            if(currDefense < maxDefense){
                // some previous character has both higher attack and higher defense
                weakCharacters ++;
            }

            maxDefense = Math.max(maxDefense, currDefense); //update if current defense is higher
        }

        return weakCharacters ;
    }
}

/*
similar : -
https://leetcode.com/problems/most-beautiful-item-for-each-query/description/
https://leetcode.com/problems/best-team-with-no-conflicts/
https://leetcode.com/problems/count-number-of-teams/description/
https://leetcode.com/problems/queue-reconstruction-by-height/description/
https://leetcode.com/problems/car-fleet/description/

 */
