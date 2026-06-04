package heap;

import java.util.*;

public class TopKFrequentElements {

    //https://leetcode.com/problems/top-k-frequent-elements/

    //Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

    /*
    Approaches
    1. Brute force : build freq map, then sort by freq. this is O(nlogn)
    2. Better : Use a frequency map followed by minheap population to retain the k most frequent elements in O(nlogk) time.
    3. Best : Use a frequency map followed by frequency buckets (bucket sort) to retrieve the k most frequent elements in O(n) time.
     */

    //USING FREQUENCY BUCKET APPROACH WHICH IS O(n)
    public int[] topKFrequent(int[] arr, int k)
    {
        //build frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>(); //{value -> freq} map

        for(int e : arr){
            frequencyMap.put(e, frequencyMap.getOrDefault(e, 0)+1);
        }

        //create freq buckets
        int n = arr.length;
        List<Integer>[] buckets = new ArrayList[n + 1];

        for(int key : frequencyMap.keySet()){
            int freq = frequencyMap.get(key);

            if(buckets[freq] == null){
                buckets[freq] = new ArrayList<>();
            }

            buckets[freq].add(key);
        }

        //now iterate buckets from end till end - k and collect result
        int[] result = new int[k];
        int idx = 0;
        for(int i=n; i>=0; i--){
            if(buckets[i] != null){
                for(int val : buckets[i]){
                    result[idx++] = val;

                    //exit condition
                    if(idx == k){
                        return result;
                    }
                }
            }
        }

        return result;
    }

    //USING HEAP APPROACH WHICH IS O(nlogk)
    public int[] topKFrequentUsingMinHeap(int[] arr, int k)
    {
        //build frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(int e : arr){
            frequencyMap.put(e, frequencyMap.getOrDefault(e, 0)+1);
        }

        //populate minheap of size k
        Queue<Integer> minheap = new PriorityQueue<>( (a,b) -> frequencyMap.get(a) - frequencyMap.get(b) );

        for(int key : frequencyMap.keySet()){
            minheap.add(key);

            if(minheap.size() > k){
                minheap.poll();
            }
        }

        //collect result array
        int[] result = new int[k];
        int idx = 0;
        while(!minheap.isEmpty()){
            result[idx++] = minheap.poll();
        }

        return result;
    }

    public static void topKFrequentUsingCountMinSketch(int[] arr, int k)
    {
        int[][] countminsketh = new int[2][5];
        Queue<Integer> minheap = new PriorityQueue<>();

        for(int e : arr){
            countminsketh[0][((e * 33 + 1)%5)] += 1;
            countminsketh[1][((e * 33 + 2)%5)] += 1;

            int freq = Math.min(countminsketh[0][((e * 33 + 1)%5)], countminsketh[1][((e * 33 + 2)%5)]);

            if(minheap.isEmpty() || freq >= minheap.peek()){
                minheap.add(e);
            }

            if(minheap.size() > k){
                minheap.remove();
            }
        }
        System.out.println(minheap);
    }

}
