package misc;

import java.util.*;

public class LeastFrequentlyUsedCacheImpl {

    public static void main(String[] args)
    {
        LFUInterface lfu = new LFUInterfaceImpl();

        lfu.put("k1", "v1");
        lfu.put("k2", "v2");
        lfu.put("k3", "v3");

        lfu.state();
        System.out.println(lfu.get("k1"));
        lfu.state();
        System.out.println(lfu.get("k1"));
        lfu.state();
        System.out.println(lfu.get("k2"));
        lfu.state();

        System.out.println(lfu.get("k3"));
        lfu.state();

        lfu.put("k4", "v4");
        lfu.state();

        System.out.println(lfu.get("k9"));
        lfu.state();
    }
}

interface LFUInterface{
    void put(String key, String val);
    String get(String key);
    int size();
    boolean isEmpty();
    void state();
}

class LFUInterfaceImpl implements LFUInterface{

    private Map<String, String> keyValmap = new HashMap<>();
    private Map<String, Integer> keyFreqmap = new HashMap<>();
    private Map<Integer, Deque<String>> freqListmap = new HashMap<>();

    private static int LIMIT = 3;
    private int leastFreq = Integer.MAX_VALUE;

    @Override
    public void put(String key, String val) {
        if(keyValmap.size() == LIMIT){
            //remove LFU, if tie, LRU
            Deque<String> deque = freqListmap.get(leastFreq);
            String last = deque.removeLast();
            keyFreqmap.remove(last);
            keyValmap.remove(last);
        }

        //put or override key val map
        keyValmap.put(key, val);

        //update frequency
        keyFreqmap.put(key, keyFreqmap.getOrDefault(key, 0)+1);

        Deque<String> deque = freqListmap.getOrDefault(keyFreqmap.get(key), new LinkedList<>());
        deque.remove(key);
        deque.addFirst(key);
        freqListmap.put(keyFreqmap.get(key), deque);

        this.updateLeastFreq(key);
    }

    @Override
    public String get(String key) {
        if(keyValmap.containsKey(key)){
            //reorder
            int freq = keyFreqmap.get(key);
            freqListmap.get(freq).remove(key);
            Deque<String> deque = freqListmap.getOrDefault(freq+1, new LinkedList<>());
            deque.addFirst(key);
            freqListmap.put(freq+1, deque);

            //increase frequency
            keyFreqmap.put(key, keyFreqmap.get(key)+1);

            //update least frequency
            this.updateLeastFreq(key);

            return keyValmap.get(key);
        }
        return null;
    }

    private void updateLeastFreq(String key) {
        leastFreq = Math.min(leastFreq, keyFreqmap.get(key));
        if(freqListmap.get(leastFreq) != null && freqListmap.get(leastFreq).isEmpty()){
            leastFreq = keyFreqmap.get(key);
        }
    }

    @Override
    public int size() {
        return keyValmap.size();
    }

    @Override
    public boolean isEmpty() {
        return keyValmap.isEmpty();
    }

    @Override
    public void state() {
        System.out.println(keyValmap);
        System.out.println(keyFreqmap);
        System.out.println(freqListmap);
    }
}
