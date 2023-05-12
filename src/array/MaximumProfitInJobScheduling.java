package array;

import java.util.*;

public class MaximumProfitInJobScheduling {

    //https://leetcode.com/problems/maximum-profit-in-job-scheduling/

    public static void main(String[] args)
    {
        System.out.println(jobScheduling(new int[]{1,2,3,3}, new int[]{3,4,5,6}, new int[]{50,10,40,70})); //120

        System.out.println(jobScheduling(new int[]{1,2,4,11,11,5}, new int[]{10,3,6,15,16,7}, new int[]{20,10,100,30,40,10})); //110

        System.out.println(jobScheduling(new int[]{1,2,3,4,6}, new int[]{3,5,10,6,9}, new int[]{20,20,100,70,60})); //150
    }

    private static int jobScheduling(int[] startTime, int[] endTime, int[] profit)
    {
        List<Job> jobs = new ArrayList<>();

        for(int i=0; i<startTime.length; i++){
            jobs.add(new Job(startTime[i], endTime[i], profit[i])); //O(n)
        }

        //sort by start time
        Collections.sort(jobs); //O(nlogn)

        //use treemap for binary search + dp memoization
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int max=0;

        for(int i=0; i<jobs.size(); i++){ //O(nlogn)
            Map.Entry<Integer, Integer> floorEntry = treeMap.floorEntry(jobs.get(i).start);//O(logn)
            int prevProfit = floorEntry == null ? 0 : floorEntry.getValue();
            int currentProfit = prevProfit + jobs.get(i).profit;
            max = Math.max(max, currentProfit);
            treeMap.put(jobs.get(i).end, currentProfit);
        }

        return max;
    }

}

class Job implements Comparable<Job>{
    int start;
    int end;
    int profit;

    public Job(int start, int end, int profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }


    @Override
    public int compareTo(Job o) {
        return Integer.compare(this.start, o.start);
    }
}
