package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ReorganizeString {
    //Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

    public static void main(String[] args)
    {
        System.out.println(reorganizeString("aaaaabbbcc")); //ababacacba
        System.out.println(reorganizeString("aaab")); //empty string as no solution
    }

    public static String reorganizeString(String input)
    {
        //build frequency count map
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for(int i=0; i<input.length(); i++){
            frequencyMap.put(input.charAt(i), frequencyMap.getOrDefault(input.charAt(i), 0)+1);
        }

        //build max heap for ech character based on frequency count
        Queue<Character> maxHeap = new PriorityQueue<>( (a, b) -> frequencyMap.get(b) - frequencyMap.get(a) );
        maxHeap.addAll(frequencyMap.keySet());

        StringBuilder sb = new StringBuilder();

        while(maxHeap.size() > 1){
            char maxChar = maxHeap.poll();
            sb.append(maxChar);
            frequencyMap.put(maxChar, frequencyMap.get(maxChar) - 1);

            char nextChar = maxHeap.poll();
            sb.append(nextChar);
            frequencyMap.put(nextChar, frequencyMap.get(nextChar) - 1);

            if(frequencyMap.get(maxChar) > 0){
                maxHeap.offer(maxChar);
            }

            if(frequencyMap.get(nextChar) > 0){
                maxHeap.offer(nextChar);
            }
        }

        if(!maxHeap.isEmpty()){
            char last = maxHeap.remove();
            if(frequencyMap.get(last) > 1){
                return "";
            }
            sb.append(last);
        }

        return sb.toString();
    }
}
