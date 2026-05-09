package google;

import java.util.*;

public class NumberOfWeakCharactersInTheGame {

    //https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/

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
                // some previous character has both:
                // higher attack and higher defense
                weakCharacters ++;
            }

            maxDefense = Math.max(maxDefense, currDefense); //update if current defense is higher
        }

        return weakCharacters ;
    }
}
