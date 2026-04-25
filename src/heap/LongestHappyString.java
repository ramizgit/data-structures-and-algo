package heap;

import java.util.*;

public class LongestHappyString {

    //https://leetcode.com/problems/longest-happy-string/description/

    public String longestDiverseString(int a, int b, int c)
    {
        PriorityQueue<CharFreq> maxheap = new  PriorityQueue<>( (x, y) -> y.freq - x.freq );
        if(a > 0){
            maxheap.offer(new CharFreq('a', a));
        }
        if(b > 0){
            maxheap.offer(new CharFreq('b', b));
        }
        if(c > 0){
            maxheap.offer(new CharFreq('c', c));
        }

        StringBuilder sb = new StringBuilder();

        while(!maxheap.isEmpty()){
            CharFreq first = maxheap.poll();

            //check if triplet found
            int len = sb.length();
            if(len >= 2 && sb.charAt(len-1) == first.ch && sb.charAt(len-2) == first.ch){

                if(maxheap.isEmpty()){
                    break;
                }

                CharFreq second = maxheap.poll();
                sb.append(second.ch);
                second.freq--;
                if(second.freq > 0){
                    maxheap.offer(second);
                }

                //add first back now
                maxheap.offer(first);
            }else{
                sb.append(first.ch);
                first.freq--;
                if(first.freq > 0){
                    maxheap.offer(first);
                }
            }
        }

        return sb.toString();
    }

    class CharFreq{
        char ch;
        int freq;

        public CharFreq(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
}
