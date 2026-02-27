package google;

import java.util.*;

/*
given a stream of messages, each with a timestamp. The goal was to design a system that:
    Considers a message "valid" only for 10 seconds (timestamp + 10).
    Ignores duplicate messages within this validity window.
    Returns the earliest valid message timestamp at any given moment.
 */

public class MessageStream {

    Queue<Message> msgStream;
    Set<String> idSet;

    private static final int VALID_WINDOW_SIZE = 10;

    public MessageStream()
    {
        this.msgStream = new ArrayDeque<>();
        this.idSet = new HashSet<>();
    }

    //add message
    public void addMessage(int timestamp, String id)
    {
        //remove expired
        removeExpiredMsg(timestamp);

        //check if dupes
        if(idSet.contains(id)){
            return; //ignore dupes
        }

        this.msgStream.add(new Message(timestamp, id));//add to msg steam
        this.idSet.add(id);//add to set
    }

    //get earlier valid message
    public int getEarlierstTimestampForValidMsg(int currentTimstamp)
    {
        //remove expired
        removeExpiredMsg(currentTimstamp);

        if(this.msgStream.isEmpty()){
            return -1;
        }

        return this.msgStream.peek().timestamp;
    }

    private void removeExpiredMsg(int currentTimstamp)
    {
        while(!this.msgStream.isEmpty() && (currentTimstamp - this.msgStream.peek().timestamp) >= VALID_WINDOW_SIZE ){
            Message msg = this.msgStream.poll();
            this.idSet.remove(msg.id);
        }
    }
}

class Message{
    int timestamp;
    String id;

    public Message(int timestamp, String id) {
        this.timestamp = timestamp;
        this.id = id;
    }
}
