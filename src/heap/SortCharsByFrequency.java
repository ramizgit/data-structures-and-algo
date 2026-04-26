package heap;

import java.util.*;

public class SortCharsByFrequency {

    //https://leetcode.com/problems/sort-characters-by-frequency/

    //TODO : TRY USING BUCKET SORT ALSO

    ////USING ARRAY TO TRACK FREQUENCY RATHER THAN MAP
    //Time: O(n log k) (k ≤ 128), Space: O(1)
    public String frequencySort(String s)
    {
        //input validation
        if(s == null || s.isEmpty()){
            return "";
        }

        //count frequency of chars
        int[] freq = new int[128]; //total number of ASCII characters including lowercase, uppercase and digits
        for(char ch : s.toCharArray()){
            freq[ch]++;
        }

        //maxheap to get max freq chars in seq
        PriorityQueue<int[]> maxheap = new PriorityQueue<>( (a, b) -> b[1] - a[1] );
        for(int i=0; i<128; i++){
            if(freq[i] > 0){
                maxheap.offer(new int[]{i, freq[i]});
            }
        }

        StringBuilder sb = new StringBuilder(); //to construct output string

        while(!maxheap.isEmpty()){
            int[] curr = maxheap.poll();
            char ch = (char) curr[0];
            int frequency = curr[1];
            for(int i=0; i<frequency; i++){
                sb.append(ch);
            }
        }

        return sb.toString();
    }


    //USING MAP
    public String frequencySortUsingMap(String s)
    {
        //input validation
        if(s == null || s.isEmpty()){
            return "";
        }

        //count frequency of chars
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : s.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        //maxheap to get max freq chars in seq
        PriorityQueue<Character> maxheap = new PriorityQueue<>( (a, b) -> freq.get(b) - freq.get(a) );
        maxheap.addAll(freq.keySet());

        StringBuilder sb = new StringBuilder(); //to construct output string

        while(!maxheap.isEmpty()){
            char ch = maxheap.poll();
            int frequency = freq.get(ch);
            for(int i=0; i<frequency; i++){
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}

