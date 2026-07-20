package greedy;

import java.util.*;

public class CourseScheduleiii {

    //https://leetcode.com/problems/course-schedule-iii/description/

    /*
    Greedy:

    1. Sort courses by deadline.
    2. Take every course (add duration to totalTime and max heap).
    3. If a deadline is exceeded, remove the longest-duration course taken so far.
    4. Heap size gives the maximum number of courses that can be completed.

    Time: O(n log n)
    Space: O(n)
    */

    public int scheduleCourse(int[][] courses)
    {
        //sort courses by asc deadline
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
                //deadline violated, remove the longest duration course to free up the most time
                totalTime -= maxheap.poll();
            }
        }

        return maxheap.size();
    }
}
