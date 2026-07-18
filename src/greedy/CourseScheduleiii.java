package greedy;

import java.util.*;

public class CourseScheduleiii {

    //https://leetcode.com/problems/course-schedule-iii/description/

    public int scheduleCourse(int[][] courses)
    {
        //sort courses by asc last day
        Arrays.sort(courses, (a, b) -> Integer.compare(a[1], b[1]));

        PriorityQueue<Integer> maxheap = new PriorityQueue<>( (a, b) -> Integer.compare(b, a) );

        int totalTime = 0;

        for(int[] course : courses){

            int duration = course[0];
            int lastDay = course[1];

            //first assume we take the course
            totalTime += duration;
            maxheap.offer(duration);

            //deadline violation check
            if (totalTime > lastDay) {
                //remove the longest duration course to free up the most time
                totalTime -= maxheap.poll();
            }
        }

        return maxheap.size();
    }
}
