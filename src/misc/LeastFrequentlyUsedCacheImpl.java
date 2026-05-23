package misc;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LeastFrequentlyUsedCacheImpl {

    interface LFUCache {
        void put(String key, String val);
        String get(String key);
        int size();
        boolean isEmpty();
    }

    class LFUCacheImpl implements LFUCache {

        private Map<String, Node> keyValueMap = new HashMap<>();
        private Map<Integer, Deque<String>> frequencyKeyListMap = new HashMap<>(); //frequency → ordered keys (for LRU within same frequency)

        private static final int CAPACITY = 3;
        private int leastFreq = 0;

        @Override
        public void put(String key, String val) {

            //edge case
            if (CAPACITY == 0) {
                return;
            }

            //scenario if key already exists in the map
            if(keyValueMap.containsKey(key)){
                Node existing = keyValueMap.get(key);
                existing.value = val; //update existing value

                get(key); // treat update as access
                return;
            }

            //scenario where its a brand new key
            if(keyValueMap.size() == CAPACITY){
                //remove LFU, if tie, LRU
                Deque<String> deque = frequencyKeyListMap.get(leastFreq); //LFU
                String lastKey = deque.removeLast(); //LRU
                if (deque.isEmpty()) {
                    frequencyKeyListMap.remove(leastFreq);
                }
                keyValueMap.remove(lastKey);
            }

            //populate map with new key val
            keyValueMap.put(key, new Node(val));
            frequencyKeyListMap.computeIfAbsent(1, k -> new LinkedList<>()).addFirst(key);

            //set least freq to 1
            leastFreq = 1;
        }

        @Override
        public String get(String key) {

            Node curr = keyValueMap.get(key);

            if (curr == null) {
                return null; //key not present in the cache
            }

            int oldFreq = curr.freq;
            int newFreq = oldFreq + 1;

            //remove from old entry and update least frequency
            Deque<String> currDeque = frequencyKeyListMap.get(oldFreq);
            currDeque.remove(key);

            if (currDeque.isEmpty()) {
                frequencyKeyListMap.remove(oldFreq);

                if (oldFreq == leastFreq) {
                    leastFreq++;
                }
            }

            curr.freq = newFreq; //update freq in key -> val map
            frequencyKeyListMap.computeIfAbsent(newFreq, k -> new LinkedList<>()).addFirst(key); //promote to new freq list

            return curr.value;
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

    class Node {
        String value;
        int freq;

        public Node(String value)
        {
            this.value = value;
            this.freq = 1;
        }
    }
}



