package heap;

import java.util.*;

public class DetectFirstTimedOutJobFromLog {

    //***GOOGLE***

    //https://codezym.com/question/138

    //Time : O(n log n)
    int firstTimedOutJobId(List<String> logs, int timeoutThreshold)
    {
        Map<Integer, Integer> jobIdStartTimeMap = new HashMap<>(); //to keep active jobs {jobId -> start time}

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
                //note : there can be multiple stale jobs at the top of the heap, and we need to remove all of them before we can trust peek()
                if(!jobIdStartTimeMap.containsKey(minheap.peek()[0])){
                    //lazy deletion : remove inactive job from heap top
                    minheap.poll(); //O(log n)
                }else{
                    //check if earliest starting job has gone timeout or not
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

                //note : we dont remove the job from heap yet due to performance reason, we do lazy deletion from heap

                //check threshold
                if(timestamp - startTime > timeoutThreshold){
                    return jobId;
                }
            }
        }

        return -1;
    }
}
