package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LongestHappyString {

    public static void main(String[] args)
    {
        System.out.println(longestDiverseString(1, 1, 7)); //ccaccbcc
        System.out.println(longestDiverseString(7, 1, 0)); //aabaa
        System.out.println(longestDiverseString(3, 0, 0)); //aa
    }

    private static String longestDiverseString(int a, int b, int c)
    {
        Map<Character, Integer> freq = new HashMap<>();
        freq.put('a', a);
        freq.put('b', b);
        freq.put('c', c);

        PriorityQueue<Character> maxheap = new PriorityQueue<>( (x,y) -> freq.get(y) - freq.get(x) );
        if(a != 0){
            maxheap.offer('a');
        }
        if(b != 0){
            maxheap.offer('b');
        }
        if(c != 0){
            maxheap.offer('c');
        }

        StringBuilder sb = new StringBuilder();

        while (maxheap.size() > 1){
            char maxChar = maxheap.poll();
            char secondMaxChar = maxheap.poll();

            if(freq.get(maxChar) >= 2){
                sb.append(maxChar).append(maxChar);
                freq.put(maxChar, freq.get(maxChar) - 2);
            }else{
                sb.append(maxChar);
                freq.put(maxChar, freq.get(maxChar) - 1);
            }

            if(freq.get(secondMaxChar) >= 2){
                sb.append(secondMaxChar).append(secondMaxChar);
                freq.put(secondMaxChar, freq.get(secondMaxChar) - 2);
            }else{
                sb.append(secondMaxChar);
                freq.put(secondMaxChar, freq.get(secondMaxChar) - 1);
            }

            //put back
            if(freq.get(maxChar) > 0){
                maxheap.offer(maxChar);
            }
            if(freq.get(secondMaxChar) > 0){
                maxheap.offer(secondMaxChar);
            }
        }

        if(!maxheap.isEmpty()){
            char maxChar = maxheap.poll();
            if(freq.get(maxChar) >= 2){
                sb.append(maxChar).append(maxChar);
            }else {
                sb.append(maxChar);
            }
        }

        return sb.toString();
    }
}

