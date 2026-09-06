package misc;

import java.util.*;

public class NotificationDeduplicator {

    private Map<String, Map<String, Long>> userNotifications;
    private Queue<Event> eventsQueue;
    private long window;

    public NotificationDeduplicator(long window){
        this.window = window;
        this.userNotifications = new HashMap<>();
        this.eventsQueue = new ArrayDeque<>();
    }

    //O(1) amortized
    public boolean shouldSend(String userId, String notificationType, long timestamp)
    {
        //expire events outside the deduplication window`1
        expireOldEvents(timestamp);

        Map<String, Long> notificationTypeToTimestamp = this.userNotifications.computeIfAbsent(userId, key -> new HashMap<>());

        Long lastTimestamp = notificationTypeToTimestamp.get(notificationType);

        if(lastTimestamp == null || timestamp - lastTimestamp >= window){

            //this is now latest
            notificationTypeToTimestamp.put(notificationType, timestamp);

            //populate events queue
            eventsQueue.offer(new Event(userId, notificationType, timestamp));

            return true;
        }

        // Still within deduplication window
        return false;
    }

    //O(1) amortized
    private void expireOldEvents(long timestamp)
    {
        long expiry = timestamp - this.window;

        while(!eventsQueue.isEmpty() && eventsQueue.peek().time <= expiry){

            Event oldEvent = eventsQueue.poll();

            Map<String, Long> notificationTypeToTimestamp = this.userNotifications.get(oldEvent.userId);

            Long timestampForType = notificationTypeToTimestamp.get(oldEvent.notifType);

            if(timestampForType != null && timestampForType == oldEvent.time){

                notificationTypeToTimestamp.remove(oldEvent.notifType);

                if(notificationTypeToTimestamp.isEmpty()){
                    this.userNotifications.remove(oldEvent.userId);
                }
            }
        }
    }

    static class Event{
        String userId;
        String notifType;
        long time;

        public Event(String userId, String notifType, long time) {
            this.userId = userId;
            this.notifType = notifType;
            this.time = time;
        }
    }
}


