package job_scheduling;

import java.util.Arrays;

public class Manager {

    public static void main(String[] args)
    {
        Job job1 = new Job("job1", 100);
        Job job2 = new Job("job2", 200);
        Job job3 = new Job("job3", 200);
        Job job4 = new Job("job4", 300);
        Job job5 = new Job("job5", 150);

        Scheduler scheduler = new Scheduler();
        scheduler.addJobs(Arrays.asList(job1, job2, job3, job4, job5));
        scheduler.run();
    }
}
