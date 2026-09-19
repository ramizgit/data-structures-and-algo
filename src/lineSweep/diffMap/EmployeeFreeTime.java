package lineSweep.diffMap;

import java.util.*;

/*
Problem: Employee Free Time

You are given the working schedules of multiple employees.

Each employee has a list of non-overlapping intervals sorted by start time.

Employee 1: [ [1,3], [6,7] ]
Employee 2: [ [2,4] ]
Employee 3: [ [2,5], [9,12] ]

Find all finite intervals during which every employee is free.

For the example above, the answer is

[[5,6], [7,9]]
Constraints
Number of employees: up to 1000
Total number of intervals across all employees: up to 100,000
Intervals are already sorted for each employee.
Intervals for a single employee never overlap.

1 <= K <= 10^4                  // number of employees

1 <= totalIntervals <= 10^5      // total intervals across all employees

1 <= schedule[i].length <= 10^5  // total over all employees <= 10^5

0 <= start < end <= 10^9
 */

public class EmployeeFreeTime {

    public List<int[]> employeeFreeTime(List<List<int[]>> schedule)
    {
        /*
        Build difference map
                ↓
        Sweep through events
                ↓
        Evaluate [prevTime, currTime)
                ↓
        If condition is satisfied: add / extend result
                ↓
        Apply event at currTime
                ↓
        move the boundary
         */

        //diff. map of {event time -> delta}
        TreeMap<Integer, Integer> events = new TreeMap<>();

        //populate schedule into diff. map
        for(List<int[]> intervals : schedule){
            for(int[] interval : intervals){

                int start = interval[0];
                int end = interval[1];

                events.put(start, events.getOrDefault(start, 0) + 1); //interval start
                events.put(end, events.getOrDefault(end, 0) - 1); //interval end
            }
        }

        //sweep through diff. map and find free slots
        List<int[]> result = new ArrayList<>();
        int currentActive = 0;

        int prevTime = -1;

        //sweep
        for(Map.Entry<Integer, Integer> event : events.entrySet()){

            int currTime = event.getKey();
            int delta = event.getValue();

            /*
            Since the end of an interval is not inclusive, the if checks the interval
            before the current event, hence we apply the current event after checking prev interval
            */

            //current interval is [prevTime, currTime)
            //currentActive = number of employees working during [prevTime, currTime)
            // 1. Check the interval before currTime
            if(prevTime != -1 && currentActive == 0){
                //merge if needed
                /*
                e.g.,
                [5,6] free
                [6,9] free
                should become [5,9]
                 */
                if(!result.isEmpty() && result.getLast()[1] == prevTime){
                    result.getLast()[1] = currTime; //expand interval end time to curr time
                }else{
                    result.add(new int[]{prevTime, currTime});
                }
            }

            // 2. Update state AT this currTime
            currentActive += delta;

            prevTime = currTime; //move the boundary
        }

        return result;
    }
}
