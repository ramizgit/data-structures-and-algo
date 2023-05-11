package job_scheduling;

public class Job implements Comparable<Job>{

    private String name;
    private int priority;

    public Job(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }


    @Override
    public int compareTo(Job o) {
        return Integer.compare(this.priority, o.priority);
    }

    public String getName() {
        return name;
    }
}
