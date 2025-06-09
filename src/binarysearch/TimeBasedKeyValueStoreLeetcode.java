package binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStoreLeetcode {
    public static void main(String[] args)
    {
        TimeMap map = new TimeMap();
        map.set("key", "val1", 2);
        map.set("key", "val2", 5);
        map.set("key", "val3", 10);
        map.set("key", "val4", 15);
        map.set("key", "val5", 20);

        System.out.println(map.get("key", 1));//val1
        System.out.println(map.get("key", 2));//val1
        System.out.println(map.get("key", 3));//val1
        System.out.println();
        System.out.println(map.get("key", 5));//val2
        System.out.println(map.get("key", 7));//val2
        System.out.println(map.get("key", 9));//val2
        System.out.println();
        System.out.println(map.get("key", 10));//val3
        System.out.println(map.get("key", 11));//val3
        System.out.println(map.get("key", 14));//val3
        System.out.println();
        System.out.println(map.get("key", 15));//val4
        System.out.println(map.get("key", 19));//val4
        System.out.println();
        System.out.println(map.get("key", 20));//val5
        System.out.println(map.get("key", 21));//val5
        System.out.println(map.get("key", 100));//val5
    }
}

class TimeMap{
    private Map<String, List<TimeValuePair>> map;

    public TimeMap()
    {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp)
    {
        this.map.computeIfAbsent(key, val -> new ArrayList<>()).add(new TimeValuePair(timestamp, value));
    }

    public String get(String key, int timestamp)
    {
        List<TimeValuePair> values = this.map.get(key);

        if(values == null || values.isEmpty()){
            return "";
        }

        //handle edge cases
        if(timestamp <= values.get(0).time){
            return values.get(0).value;
        }
        if(timestamp >= values.get(values.size()-1).time){
            return values.get(values.size()-1).value;
        }

        //else do binary search
        int low = 0;
        int high = values.size()-1;
        String result = "";

        while (low <= high){
            int mid = low + (high - low)/2;

            if (values.get(mid).time == timestamp) {
                return values.get(mid).value;
            } else if (values.get(mid).time < timestamp) {
                result = values.get(mid).value; //potential answer, might be overriden if a more suitable time matching found later
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }
}

class TimeValuePair{
    int time;
    String value;

    public TimeValuePair(int time, String value) {
        this.time = time;
        this.value = value;
    }
}
