package heap;

import java.util.*;

public class ReorganizeString {

    //Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

    public static String reorganizeString(String input)
    {
        //input validation
        if(input == null || input.isEmpty()){
            return "";
        }

        //count frequency
        int[] freq = new int[26];
        for(char ch : input.toCharArray()){
            freq[ch - 'a']++;
        }

        //populate max heap with {char, freq} pairs
        PriorityQueue<int[]> maxheap = new PriorityQueue<>( (a, b) -> b[1] - a[1] );
        for(int i=0; i<26; i++){
            maxheap.offer(new int[]{i, freq[i]});
        }

        StringBuilder sb = new StringBuilder();

        while(maxheap.size() > 1){
            int[] first = maxheap.poll();
            int[] second = maxheap.poll();

            sb.append((char) (first[0] + 'a'));
            sb.append((char) (second[0] + 'a'));

            first[1]--;
            second[1]--;

            if(first[1] > 0){
                maxheap.offer(first);
            }
            if(second[1] > 0){
                maxheap.offer(second);
            }
        }

        if(!maxheap.isEmpty()){
            int[] last = maxheap.poll();
            if(last[1] > 1){
                return "";
            }
            sb.append((char) (last[0] + 'a'));
        }

        return sb.toString();
    }
}
