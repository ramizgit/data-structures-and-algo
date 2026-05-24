package google.cache;

import java.util.HashMap;
import java.util.Map;

/*
this implementation is O(1) for get and put
refer "LeastRecentlyUsedCacheImpl" for O(n) get and put
 */

public class LeastRecentlyUsedCacheImplOrderOfOne {

    interface LRUCache{
        //key functions
        void put(String key, String value);
        String get(String key);

        //additional metadata functions
        int size();
        boolean isEmpty();
    }

    class LRUCacheImpl implements LRUCache{

        Map<String, Node> keyValueMap = new HashMap<>();
        DoublyLinkedList keyOrderedList = new DoublyLinkedList();

        private static final int CAPACITY = 3;

        @Override
        public void put(String key, String value) {

            //edge case
            if (CAPACITY == 0) {
                return;
            }

            //scenario if key already exists in the map
            if(keyValueMap.containsKey(key)){
                Node existing = keyValueMap.get(key);
                existing.value = value;//override existing value

                get(key); // treat update as access
                return;
            }

            //scenario where it's a new key
            if (keyValueMap.size() == CAPACITY) { //capacity check
                //remove LRU element since capacity has reached max
                Node lru = keyOrderedList.removeLast();
                keyValueMap.remove(lru.key);
            }

            Node node = new Node(key, value);

            keyValueMap.put(key, node);
            keyOrderedList.addFirst(node);
        }

        @Override
        public String get(String key) {

            Node curr = keyValueMap.get(key);

            if (curr == null) {
                return null; //key not present in the cache, fetch from db and populate map or throw exception
            }

            //reorder the dequeue
            keyOrderedList.remove(curr); //O(1) operation
            keyOrderedList.addFirst(curr);
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

    class Node{
        String key;
        String value;

        //pointers for O(1) removal
        Node prev;
        Node next;

        public Node() {
        }

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoublyLinkedList{

        Node head;
        Node tail;
        int size;

        public DoublyLinkedList() {
            this.head = new Node();
            this.tail = new Node();

            //connect head to tail and vice versa
            head.next = tail;
            tail.prev = head;

            this.size = 0;
        }

        //add first
        public void addFirst(Node node){
            Node nextNode = head.next;

            head.next = node;
            node.prev = head;

            node.next = nextNode;
            nextNode.prev = node;

            size++;
        }

        public void remove(Node node){
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            size--;
        }

        //remove last
        public Node removeLast(){

            if(isEmpty()){
                return null;
            }

            Node node = tail.prev;
            remove(node);
            return node;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }
}
