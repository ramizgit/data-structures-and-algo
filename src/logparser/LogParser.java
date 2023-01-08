package logparser;

import java.util.Objects;

public class LogParser {

    public static void main(String[] args)
    {
        //create objects for Entry
        //sort them
        //
    }
}

class Entry implements Comparable{
    int timestamp;
    String user;
    String resource;

    public Entry(int timestamp, String user, String resource) {
        this.timestamp = timestamp;
        this.user = user;
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return timestamp == entry.timestamp &&
                user.equals(entry.user) &&
                resource.equals(entry.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, user, resource);
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public int compareTo(Object o) {
        Entry entry = (Entry) o;
        return this.timestamp - entry.getTimestamp();
    }
}
