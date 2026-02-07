package array;

import java.util.*;

public class PartitionArrayOntoKDistinctGroups {
    //https://leetcode.com/problems/partition-array-into-k-distinct-groups/description/
    public static void main(String[] args)
    {

    }

    //0(nlogn)
    private static boolean partitionArray(int[] nums, int k)
    {
        if(nums == null || nums.length % k != 0){
            return false;
        }

        int groups = nums.length / k;

        //build freq map
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums){ //0(n)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // if any number appears more than groups → impossible
        for (int count : freq.values()) { //0(d)
            if (count > groups) {
                return false;
            }
        }

        //build max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>( (a, b) -> b - a );
        pq.addAll(freq.keySet()); //0(dlogd)

        while(!pq.isEmpty()){ //0(nlogd)
            List<Integer> used = new ArrayList<>();

            for(int i=0; i<k; i++){
                if(pq.isEmpty()){
                    return false;
                }

                int curr = pq.poll();
                freq.put(curr, freq.get(curr) - 1);

                if(freq.get(curr) > 0){
                    used.add(curr);
                }
            }

            //add back to pq
            pq.addAll(used);
        }

        return true;
    }
}
