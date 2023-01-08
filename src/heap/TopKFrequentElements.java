package heap;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args)
    {
        int[] arr = {8,1,3,2,6,7,9,6,6,6,7,7,7,7,1,1,1,1,1,1,1,1,1};

        findTopKFrequentElements(arr, 3);
        findTopKFrequentElementsUsingCountMinSketch(arr, 3);
    }

    public static void findTopKFrequentElements(int[] arr, int k)
    {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        //Queue<Integer> minheap = new PriorityQueue<>(Comparator.comparingInt(frequencyMap::get));
        Queue<Integer> minheap = new PriorityQueue<>( (a,b) -> frequencyMap.get(a) - frequencyMap.get(b) );

        //capture frequency
        for(int e : arr){
            frequencyMap.put(e, frequencyMap.getOrDefault(e, 0)+1);
        }

        for(int key : frequencyMap.keySet()){
            minheap.add(key);

            if(minheap.size() > k){
                minheap.remove();
            }
        }

        System.out.println(minheap);
    }

    public static void findTopKFrequentElementsUsingCountMinSketch(int[] arr, int k)
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
