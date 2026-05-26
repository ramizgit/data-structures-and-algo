package binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStoreLeetcode {

    class TimeBasedKeyValueStore {

        private Map<String, List<Value>> keyValuesMap;

        public TimeBasedKeyValueStore()
        {
            this.keyValuesMap = new HashMap<>();
        }

        //Time complexity : O(1)
        public void set(String key, String value, int timestamp)
        {
            this.keyValuesMap.computeIfAbsent(key, val -> new ArrayList<>()).add(new Value(timestamp, value));
        }

        //Time complexity : O(logn)
        public String get(String key, int timestamp)
        {
            List<Value> values = this.keyValuesMap.get(key);

            //handle edge cases
            if(values == null || values.isEmpty()){
                return "";
            }

            if(timestamp < values.getFirst().time){
                return ""; //no valid answer exists since requested timestamp is SMALLER than first timestamp
            }

            if(timestamp >= values.getLast().time){
                return values.getLast().value;
            }

            //else do binary search on values list
            int low = 0;
            int high = values.size()-1;
            String result = "";

            while (low <= high){

                int mid = low + (high - low)/2;

                if (values.get(mid).time <= timestamp) {
                    result = values.get(mid).value; //potential answer, might be overriden if a more suitable time matching found later
                    low = mid + 1; //try higher timestamp
                } else {
                    high = mid - 1; //try lower timestamp
                }
            }
            return result;
        }
    }

    class Value {
        int time;
        String value;

        public Value(int time, String value) {
            this.time = time;
            this.value = value;
        }
    }
}


