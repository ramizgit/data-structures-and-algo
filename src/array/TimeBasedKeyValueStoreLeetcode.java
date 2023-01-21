package array;

import javafx.util.Pair;

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
    private Map<String, List<Pair<Integer, String>>> map;

    public TimeMap()
    {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp)
    {
        List<Pair<Integer, String>> values = this.map.getOrDefault(key, new ArrayList<>());
        values.add(new Pair<>(timestamp, value));
        this.map.put(key, values);
    }

    public String get(String key, int timestamp)
    {
        List<Pair<Integer, String>> values = this.map.get(key);

        if(values == null || values.isEmpty()){
            return "";
        }

        //handle edge cases
        if(timestamp <= values.get(0).getKey()){
            return values.get(0).getValue();
        }
        if(timestamp >= values.get(values.size()-1).getKey()){
            return values.get(values.size()-1).getValue();
        }

        //else do binary search
        int start = 0;
        int end = values.size()-1;

        while (start <= end){
            int mid = (start + end) >>> 1;

            if(timestamp == values.get(mid).getKey() || (timestamp > values.get(mid).getKey() && timestamp < values.get(mid+1).getKey())){
                return values.get(mid).getValue();
            }else if(timestamp < values.get(mid).getKey() && timestamp > values.get(mid-1).getKey()){
                return values.get(mid-1).getValue();
            }else if(timestamp < values.get(mid).getKey()){
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return "";
    }
}
