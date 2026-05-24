package google.cache;

import java.util.*;

/*
this implementation is O(1) for get and put
following "LeastFrequentlyUsedCacheImpl" for O(n) get and put
 */

public class LeastFrequentlyUsedCacheImplOrderOfOne {

    interface LFUCache{
        //key functions
        void put(String key, String value);
        String get(String key);

        //additional metadata functions
        int size();
        boolean isEmpty();
    }

    class LFUCacheImpl implements LFUCache{

        private Map<String, Node> keyValueMap = new HashMap<>();
        private Map<Integer, DoublyLinkedList> frequencyListMap = new HashMap<>();

        private static final int CAPACITY = 3;
        private int leastFreq = 0;

        @Override
        public void put(String key, String value) {

            //edge case
            if (CAPACITY == 0) {
                return;
            }

            //scenario if key already exists in the map
            if(keyValueMap.containsKey(key)){
                Node existing = keyValueMap.get(key);
                existing.value = value; //update existing value

                get(key); // treat update as access
                return;
            }

            //scenario where its a brand new key
            if(keyValueMap.size() == CAPACITY){
                //remove LFU, if tie, LRU
                DoublyLinkedList doublyLinkedList = frequencyListMap.get(leastFreq); //LFU
                Node lastKey = doublyLinkedList.removeLast(); //LRU tie-breaking
                if (doublyLinkedList.isEmpty()) {
                    frequencyListMap.remove(leastFreq);
                }
                keyValueMap.remove(lastKey.key);
            }

            //populate map with new key val
            Node node = new Node(key, value);
            keyValueMap.put(key, node);
            frequencyListMap.computeIfAbsent(1, k -> new DoublyLinkedList()).addFirst(node);

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
            DoublyLinkedList linkedList = frequencyListMap.get(oldFreq);
            linkedList.remove(curr); //O(1)

            if (linkedList.isEmpty()) {
                frequencyListMap.remove(oldFreq);

                if (oldFreq == leastFreq) {
                    leastFreq++;
                }
            }

            curr.freq = newFreq; //update freq in key -> val map
            frequencyListMap.computeIfAbsent(newFreq, k -> new DoublyLinkedList()).addFirst(curr); //promote to new freq list

            return curr.value;
        }

        @Override
        public int size() {
            return this.keyValueMap.size();
        }

        @Override
        public boolean isEmpty() {
            return this.keyValueMap.isEmpty();
        }
    }

    class Node{
        String key;
        String value;
        int freq;

        //pointers for O(1) removal
        Node prev;
        Node next;

        public Node() {
        }

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
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
