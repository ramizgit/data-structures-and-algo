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
        //scenario if key already exists in the map
        if(keyValmap.containsKey(key)){
            keyValmap.put(key, val);
            get(key); // treat update as access
            return;
        }

        //scenario where its a brand new key
        if(keyValmap.size() == LIMIT){
            //remove LFU, if tie, LRU
            Deque<String> deque = freqListmap.get(leastFreq); //LFU
            String last = deque.removeLast(); //LRU
            keyFreqmap.remove(last);
            keyValmap.remove(last);
        }

        //populate map with new key val
        keyValmap.put(key, val);

        //set frequency to 1 for new key and add to deque
        keyFreqmap.put(key, 1);
        freqListmap.computeIfAbsent(1, k -> new LinkedList<>()).addFirst(key);

        //set least freq to 1
        leastFreq = 1;
    }

    @Override
    public String get(String key) {
        if (!keyValmap.containsKey(key)) return null;

        int prevFreq = keyFreqmap.get(key);
        freqListmap.get(prevFreq).remove(key); //remove from prev deque
        //update least frequency
        if (freqListmap.get(prevFreq).isEmpty() && prevFreq == leastFreq) {
            leastFreq++;
        }

        int newFreq = prevFreq + 1;
        keyFreqmap.put(key, newFreq); //set new frequency
        freqListmap.computeIfAbsent(newFreq, k -> new LinkedList<>()).addFirst(key); //promote to new freq list

        return keyValmap.get(key);
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
        System.out.println("Sate Start");
        System.out.println(keyValmap);
        System.out.println(keyFreqmap);
        System.out.println(freqListmap);
        System.out.println("Sate End");
    }
}
