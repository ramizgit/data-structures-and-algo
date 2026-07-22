package sliding_window;

import java.util.*;

public class FruitIntoBaskets {

    //https://leetcode.com/problems/fruit-into-baskets/description/

    public int totalFruit(int[] fruits)
    {
        //pattern : Longest subarray with at most 2 distinct integers

        //input validation
        if(fruits == null || fruits.length == 0){
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>(); //map to track frequency of picked fruits

        int windowStart = 0;
        int maxFruits = 0;

        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {

            //pick current fruit and increase its freq
            int currentFruit = fruits[windowEnd];
            freq.put(currentFruit, freq.getOrDefault(currentFruit, 0) + 1);

            //shrink until the window contains at most 2 fruit types
            while(freq.size() > 2){

                int leftFruit = fruits[windowStart];

                freq.put(leftFruit, freq.get(leftFruit) - 1);
                if(freq.get(leftFruit) == 0){
                    freq.remove(leftFruit);
                }

                windowStart++;
            }

            maxFruits = Math.max(maxFruits, windowEnd - windowStart + 1);
        }

        return maxFruits;
    }
}
