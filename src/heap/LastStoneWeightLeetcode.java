package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeightLeetcode {

    public static void main(String[] args)
    {
        System.out.println(lastStoneWeight(new int[]{2,7,4,1,8,1})); //1
        System.out.println(lastStoneWeight(new int[]{1})); //1
        System.out.println(lastStoneWeight(new int[]{2,2})); //0
        System.out.println(lastStoneWeight(new int[]{2,1,2})); //1
        System.out.println(lastStoneWeight(new int[]{})); //0
    }

    private static int lastStoneWeight(int[] stones)
    {
        //initialize max heap
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());

        //add all stones to the max heap
        for(int stone : stones){
            maxheap.offer(stone);
        }

        while (maxheap.size() > 1){
            int haviestStone = maxheap.poll();
            int secondHaviestStone = maxheap.poll();

            int diff = haviestStone - secondHaviestStone;

            if(diff != 0){
                maxheap.offer(diff);
            }
        }

        if(maxheap.size() == 1){
            return maxheap.poll();
        }else {
            return 0;
        }
    }
}
