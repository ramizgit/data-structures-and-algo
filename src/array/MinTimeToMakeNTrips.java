package array;

import javafx.util.Pair;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinTimeToMakeNTrips {
    public static void main(String[] args)
    {
        //System.out.println(findMinTime(new int[]{1, 2}, 3));
        System.out.println(findMinTime(new int[]{1,3,5,7,8}, 10));
        //System.out.println(findMinTime(new int[]{3,4,8}, 3));

    }

    public static int findMinTime(int[] arr, int n)
    {
        Queue<Pair<Integer, Integer>> minheap = new PriorityQueue<>( (p1, p2) -> p1.getKey() - p2.getKey() );
        //Queue<Pair<Integer, Integer>> minheap = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));

        for(int e : arr){
            minheap.add(new Pair<>(e, e));
        }

        int minTime = 0;

        for(int i=0; i<n; i++){
            Pair<Integer, Integer> minPair = minheap.remove();

            minTime = Math.max(minTime, minPair.getKey());

            minheap.add(new Pair<>(minPair.getKey()+minPair.getValue(), minPair.getValue()));
        }

        return minTime;
    }
}
