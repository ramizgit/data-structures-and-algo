package consistenthashing.intervals;

import java.util.*;

public class MergeWorkingHourIntervalTimeline {

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