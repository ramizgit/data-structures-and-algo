package slidingWindow;

import java.util.*;

public class FruitIntoBaskets {

    public static void main(String[] args)
    {

    }

    public int totalFruit(int[] fruits)
    {
        if(fruits == null || fruits.length == 0){
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();

        int left = 0;
        int right = 0;
        int maxFruits = 0;

        while(right < fruits.length){
            freq.put(fruits[right], freq.getOrDefault(fruits[right], 0) + 1);

            while(freq.size() > 2){
                int l = fruits[left];
                freq.put(l, freq.get(l) - 1);

                if(freq.get(l) == 0){
                    freq.remove(l);
                }

                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);

            right++;
        }

        return maxFruits;
    }
}
