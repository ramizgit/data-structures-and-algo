package google.cache;

import java.util.*;

/*
this implementation is O(n) for get and put
 */

public class LeastRecentlyUsedCacheImpl {

    interface LRUCache{
        //key functions
        void put(String key, String value);
        String get(String key);

        //additional metadata functions
        int size();
        boolean isEmpty();
    }

    class LRUCacheImpl implements LRUCache{

        Map<String, String> keyValueMap = new HashMap<>();
        Deque<String> keyOrderedList = new ArrayDeque<>();

        private static final int CAPACITY = 3;

        @Override
        public void put(String key, String value) {

            //edge case
            if (CAPACITY == 0) {
                return;
            }

            //scenario if key already exists in the map
            if(keyValueMap.containsKey(key)){
                keyValueMap.put(key, value); //override existing value
                get(key); // treat update as access
                return;
            }

            //scenario where it's a new key
            if (keyValueMap.size() == CAPACITY) { //capacity check
                //remove LRU element since capacity has reached max
                String lru = keyOrderedList.removeLast();
                keyValueMap.remove(lru);
            }

            keyValueMap.put(key, value);
            keyOrderedList.addFirst(key);
        }

        @Override
        public String get(String key) {

            if(keyValueMap.containsKey(key)) {
                //reorder the dequeue
                keyOrderedList.remove(key); //O(n) operation
                keyOrderedList.addFirst(key);
                return keyValueMap.get(key);
            }else{
                //fetch from db and populate map or throw exception
                return null;
            }
        }

        @Override
        public int size() {
            return keyValueMap.size();
        }

        @Override
        public boolean isEmpty() {
            return keyValueMap.isEmpty();
        }
    }
}
