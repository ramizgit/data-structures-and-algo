package slidingWindow;

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

        int left = 0;
        int right = 0;
        int maxFruits = 0;

        while(right < fruits.length){

            //pick current fruit and increase its freq
            int currentFruit = fruits[right];
            freq.put(currentFruit, freq.getOrDefault(currentFruit, 0) + 1);

            while(freq.size() > 2){
                int leftFruit = fruits[left];
                freq.put(leftFruit, freq.get(leftFruit) - 1);

                if(freq.get(leftFruit) == 0){
                    freq.remove(leftFruit);
                }

                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);

            right++;
        }

        return maxFruits;
    }
}
