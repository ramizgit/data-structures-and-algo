package consistenthashing.heap;

import java.util.*;

public class DetectFirstTimedOutJobFromLog {

    //***GOOGLE***

    //https://codezym.com/question/138

    //Time : O(n log n)
    int firstTimedOutJobId(List<String> logs, int timeoutThreshold)
    {
        Map<Integer, Integer> jobIdStartTimeMap = new HashMap<>(); //to keep active jobs

        //minheap of {job id, start time}
        PriorityQueue<int[]> minheap = new PriorityQueue<>(
                (a, b) -> {

                    if(a[1] == b[1]){
                        return a[0] - b[0]; //order by job id asc order if tie happens
                    }

                    return a[1] - b[1]; //order by start time asc order
                }
        );

        for(String log : logs){

            String[] entry = log.split(",");

            int jobId = Integer.parseInt(entry[0]);
            int timestamp = Integer.parseInt(entry[1]);
            String eventType = entry[2];

            //heap cleanup
            while(!minheap.isEmpty()){
                if(!jobIdStartTimeMap.containsKey(minheap.peek()[0])){
                    //remove inactive jobs
                    minheap.poll(); //O(log n)
                }else{
                    int[] earliestJob = minheap.peek();
                    int earliestJobId = earliestJob[0];
                    int earliestJobStartTime = earliestJob[1];

                    //check threshold
                    if(timestamp - earliestJobStartTime > timeoutThreshold){
                        return earliestJobId;
                    }else{
                        break;
                    }
                }
            }

            if("START".equals(eventType)){
                jobIdStartTimeMap.put(jobId, timestamp);
                minheap.offer(new int[]{jobId, timestamp}); //O(log n)
            }else if("END".equals(eventType)){
                int startTime = jobIdStartTimeMap.get(jobId);
                jobIdStartTimeMap.remove(jobId);

                //check threshold
                if(timestamp - startTime > timeoutThreshold){
                    return jobId;
                }
            }
        }

        return -1;
    }
}
