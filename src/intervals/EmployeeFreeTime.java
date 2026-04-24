package intervals;

import java.util.*;

public class EmployeeFreeTime {

    //https://leetcode.com/problems/employee-free-time/description/

    /*
    You are given a list of employees, where each employee has a list of non-overlapping working intervals
    (sorted by start time). Each interval represents a time period during which that employee is busy.
    Your task is to find all common free time intervals — i.e., the time intervals during which every employee is free.
     */

    public List<int[]> employeeFreeTime(List<List<int[]>> schedule)
    {
        //flatten input schedules
        List<int[]> flattenSchedule = new ArrayList<>();
        for(List<int[]> s : schedule){
            flattenSchedule.addAll(s);
        }

        //global sort by start time
        Collections.sort(flattenSchedule, (a, b) -> a[0] - b[0]);

        //merge schedules
        List<int[]> mergeSchedule = new ArrayList<>();
        mergeSchedule.add(flattenSchedule.get(0)); //first schedule

        for(int i=1; i<flattenSchedule.size(); i++){
            int[] curr = flattenSchedule.get(i);
            int[] last = mergeSchedule.getLast();

            if(curr[0] <= last[1]){
                last[1] = Math.max(last[1], curr[1]); //overlap found, merge schedules
            }else{
                mergeSchedule.add(curr); //no overlap, add current schedule
            }
        }

        //iterate and find gaps
        List<int[]> result = new ArrayList<>();
        for(int i=1; i<mergeSchedule.size(); i++){
            int[] curr = mergeSchedule.get(i);
            int[] prev = mergeSchedule.get(i-1);

            int[] free = {prev[1], curr[0]};

            result.add(free);
        }

        return result;
    }
}
