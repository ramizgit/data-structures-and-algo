package consistenthashing.differenceArray;

/*
You are given a list of records and an integer d representing the range of days from 1 to d.

Each record represents one blocked interval for one person.

Each record is given as a string in the format "id,start,end".

A record "id,start,end" means person id is not available on every day from start to end, inclusive.

Return all days between 1 and d on which every person is free. This list will be sorted in ascending order.

Follow-up: return all days on which the number of available people is greater than or equal to k.

For the purpose of this problem, the set of people is the set of distinct id values present in records.
Class
FreeDaysFinder
Methods
List<Integer> getDaysWhenEveryoneIsFree(List<String> records, int d)
Returns all days from 1 to d on which every person is free.
Each string in records has format "id,start,end".
A person is blocked on every day in the inclusive range [start, end].
A day is included only if all distinct people in records are available on that day.
The returned list must be in increasing order.
List<Integer> getDaysWhenAtLeastKPeopleAreFree(List<String> records, int d, int k)
Returns all days from 1 to d on which at least k people are free.
Each string in records has format "id,start,end".
A person is blocked on every day in the inclusive range [start, end].
The number of free people on a day is computed using the distinct people present in records.
The returned list must be in increasing order.
Constraints
1 ≤ d ≤ 100,000
1 ≤ records.size() ≤ 100,000
1 ≤ id ≤ 10,000,000
1 ≤ start ≤ end ≤ d
Each record string is in the format "id,start,end".
1 ≤ k ≤ number of distinct ids in records
Multiple records may exist for the same person.
Notes
Day numbers are inclusive and start from 1.
If a person has multiple records, that person is unavailable on the union of all blocked days from those records.
If no day satisfies the condition, return an empty list.
 */

import java.util.*;

public class DaysWhenEveryoneIsFree {

    //https://codezym.com/question/154

    //Technique : Difference Array [Range Update + Prefix Sum]

    //returns all days between 1 and d on which every person is free.
    public List<Integer> getDaysWhenEveryoneIsFree(String[] records, int d)
    {
        //initialzie difference array
        int[] days = new int[d+2];

        for(String record : records){

            String[] entry = record.split(",");

            int start = Integer.parseInt(entry[1]);
            int end = Integer.parseInt(entry[2]);

            //range update
            days[start] += 1;
            days[end+1] -= 1;
        }

        //prefix sum
        List<Integer> result = new ArrayList<>();
        int blockedCount = 0; //prefix sum

        for(int day=1; day<=d; day++){
            blockedCount += days[day];

            if(blockedCount == 0){
                result.add(day);
            }
        }

        return result;
    }

    /*
    Part 1 only cares whether the blocked count is zero or non-zero, so duplicate counting doesn't affect correctness.
    Part 2 needs the exact number of blocked people, so intervals for the same person must be merged first.
     */
    List<Integer> getDaysWhenAtLeastKPeopleAreFree(String[] records, int d, int k)
    {
        //group by {person -> list of intervals}
        Map<Integer, List<int[]>> personToIntervals = new HashMap<>();

        for(String record : records){

            String[] entry = record.split(",");
            int id = Integer.parseInt(entry[0]);
            int start = Integer.parseInt(entry[1]);
            int end = Integer.parseInt(entry[2]);

            personToIntervals.computeIfAbsent(id, key -> new ArrayList<>()).add(new int[]{start, end});
        }

        //run merge interval against each person
        for(int person : personToIntervals.keySet()){

            List<int[]> currIntervals = personToIntervals.get(person);
            List<int[]> mergedIntervals = mergeIntervals(currIntervals);
            personToIntervals.put(person, mergedIntervals);
        }

        //initialzie difference array
        int[] days = new int[d+2];

        for(List<int[]> intervals : personToIntervals.values()){
            for(int[] interval : intervals){
                int start = interval[0];
                int end = interval[1];

                days[start] += 1;
                days[end+1] -= 1;
            }
        }

        //prefix sum
        List<Integer> result = new ArrayList<>();
        int blockedCount = 0; //prefix sum
        int totalPersonCount = personToIntervals.size();

        for(int day=1; day<=d; day++){
            blockedCount += days[day];

            int available = totalPersonCount - blockedCount;

            if(available >= k){
                result.add(day);
            }
        }

        return result;
    }

    private List<int[]> mergeIntervals(List<int[]> intervals)
    {
        //sort by start time
        Collections.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals.getFirst());

        for(int i=1; i<intervals.size(); i++){
            int[] curr = intervals.get(i);
            int currStartTime = curr[0];

            int[] last = result.getLast();
            int lastEndTime = last[1];

            if(currStartTime <= lastEndTime + 1){
                //overlap, extend last interval
                last[1] = Math.max(last[1], curr[1]);
            }else{
                result.add(curr);
            }
        }

        return result;
    }

}
