import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCacheImplementation {
    public static void main(String[] args)
    {

    }
}

interface LRUCache{
    void put(int key, String val);
    String get(int key);
    int size();
    boolean isEmpty();
}

class LRUCacheImpl implements LRUCache{

    Map<Integer, String> map = new HashMap<>();
    Deque<String> deque = new LinkedList<>();

    @Override
    public void put(int key, String val) {
        //TODO : check size limitation
        if(map.containsKey(key)) {
            //remove from dequeue
            deque.remove(val);
        } else if (map.size() == 1000) {
            //remove LRU element
            deque.removeLast();
        }
        //put or override value in the map
        map.put(key, val);
        //add to dequeue at the head
        deque.addFirst(val);
    }

    @Override
    public String get(int key) {

        if(map.containsKey(key)) {
            //reorder the dequeue
            deque.remove(map.get(key));
            deque.addFirst(map.get(key));
            return map.get(key);
        }else{
            //fetch from db and populate map or throw exception
            return null;
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
