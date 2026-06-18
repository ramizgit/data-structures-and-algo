package consistenthashing.intervals;

import java.util.*;

/*
Person name and their working hours are given.

Return the timeline that tells the time interval and whoever is working during that interval.

Each person is represented by one string entry containing the person name, start time, and end time.

A person is considered working at both startTime and endTime.

Split the full timeline into the smallest non-overlapping time intervals such that the set of working people stays the same throughout each interval.

Return only the intervals where at least one person is working.

If multiple people are working in the same interval, return their names in lexicographical order, separated by ", ".

Consecutive intervals with the same set of working people must be merged into one interval.
Class
WorkingHoursTimeline
Methods
WorkingHoursTimeline()
Initializes the object.
List<String> getWorkingTimeline(List<String> workingHours)
Each element in workingHours is formatted as "name,startTime,endTime".
name is the person's name.
Each person has a unique name.
A person's name never contains the comma character.
startTime is the time when that person starts working.
endTime is the time when that person stops working.
Return a list of strings formatted as "startTime to endTime -> name1, name2, ...".
Each returned interval must contain exactly the people working during the full interval.
The returned list must be ordered by interval start time.
Names inside each returned string must be in lexicographical order.
Constraints
1 ≤ workingHours.size() ≤ 10^4
1 ≤ name.length() ≤ 100
Each workingHours[i] is formatted as "name,startTime,endTime".
0 ≤ hour < 24
0 ≤ minute < 60
Each time is in 24-hour hh:mm format such as 01:00, 05:30, or 14:00.
Each time value represents a whole minute.
For every entry, startTime ≤ endTime.
workingHours never contains null values.
Notes
A person is considered working for every minute from startTime through endTime, both inclusive.
If no one is working in a time range, that range must not appear in the returned list.
If one person ends at the same time another person starts, both are considered working at that time.
If startTime and endTime of a returned interval are equal, that interval represents exactly one minute.
If multiple people start or end work at the same time, process that boundary correctly and return the resulting active set for that minute and later minutes.
 */

public class MergeWorkingHourIntervalTimeline {

    //***GOOGLE

    //https://codezym.com/question/153

    List<String> getWorkingTimeline(List<String> workingHours)
    {
        // TreeMap keeps event times sorted
        Map<Integer, List<Event>> events = new TreeMap<>();

        // build events
        for (String workingHour : workingHours)
        {
            String[] entry = workingHour.split(",");

            String name = entry[0];
            String startTime = entry[1];
            String endTime = entry[2];

            int start = toMinute(startTime);
            int end = toMinute(endTime);

            events.computeIfAbsent(start, k -> new ArrayList<>()).add(new Event(name, "START"));

            // remove after inclusive end minute
            events.computeIfAbsent(end + 1, k -> new ArrayList<>()).add(new Event(name, "END"));
        }

        int prevTime = -1;

        TreeSet<String> activeWorkers = new TreeSet<>();

        List<String> result = new ArrayList<>();

        for (Map.Entry<Integer, List<Event>> entry : events.entrySet())
        {
            int time = entry.getKey();

            // interval between prevTime and time-1
            if (prevTime != -1 && !activeWorkers.isEmpty())
            {
                String start = toTime(prevTime);
                String end = toTime(time - 1);

                String workers = String.join(", ", activeWorkers);

                result.add(start + " to " + end + " -> " + workers);
            }

            // process events at current time
            for (Event event : entry.getValue())
            {
                if ("START".equals(event.eventType)) {
                    activeWorkers.add(event.name);
                }
                else {
                    activeWorkers.remove(event.name);
                }
            }

            prevTime = time;
        }

        return result;
    }

    private int toMinute(String time)
    {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour * 60 + minute;
    }

    private String toTime(int totalMinutes)
    {
        int hour = totalMinutes / 60;
        int minute = totalMinutes % 60;

        return String.format("%02d:%02d", hour, minute);
    }

    class Event
    {
        String name;
        String eventType; // START or END

        public Event(String name, String eventType)
        {
            this.name = name;
            this.eventType = eventType;
        }
    }
}