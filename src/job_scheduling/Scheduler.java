package job_scheduling;

import java.util.List;
import java.util.PriorityQueue;

public class Scheduler {

    PriorityQueue<Job> minheap = new PriorityQueue<>();

    public void addJobs(List<Job> jobs){
        minheap.addAll(jobs);
    }

    public void run(){

        while (!minheap.isEmpty()){
            Job job = minheap.poll();
            System.out.println("Started running job : "+job.getName());

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Finished running job : "+job.getName());
        }
    }
}
