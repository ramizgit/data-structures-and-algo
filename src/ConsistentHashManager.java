package consistenthashing;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashManager {

    TreeMap<Integer, String> serverHash = new TreeMap<>();
    TreeMap<Integer, String> keyHash = new TreeMap<>();
    Map<String, String> keyToServerMap = new HashMap<>();

    public static int RING_LIMIT = 100;

    public void addServer(String server)
    {
        int hash = this.getHash(server);

        //get floor entry to check if any rehashing required
        Map.Entry<Integer, String> floorEntry = this.serverHash.floorEntry(hash);

        //populate server hash id to server name map
        this.serverHash.put(hash, server);

        //logging
        System.out.println("hash for server : "+server+" is : "+hash);

        if(floorEntry != null){
            SortedMap<Integer, String> subMap = this.keyHash.subMap(floorEntry.getKey(), hash);
            if(subMap.isEmpty()){
                System.out.println("no rehashing required after adding server :"+server);
            }else {
                System.out.println("Rehashing is required after adding server :"+server);
                for(Map.Entry<Integer, String> entry : subMap.entrySet()){
                    this.addKey(entry.getValue());
                }
            }
        }
    }

    public void removeServer(String server)
    {
        int hash = this.getHash(server);

        this.serverHash.remove(hash);

        //get floor and ceiling entry to check if any rehashing required
        Map.Entry<Integer, String> floorEntry = this.serverHash.floorEntry(hash);
        Map.Entry<Integer, String> ceilingEntry = this.serverHash.ceilingEntry(hash);

        int start = floorEntry != null ? floorEntry.getKey() : this.serverHash.lastKey();
        int end = ceilingEntry != null ? ceilingEntry.getKey() : this.serverHash.firstKey();

        //if start > end, then break into two submaps, start -- MAX, MIN -- end
        if(start < end){
            for(Map.Entry<Integer, String> entry : this.keyHash.subMap(start, end).entrySet()){
                this.addKey(entry.getValue());
            }
        }else {
            for(Map.Entry<Integer, String> entry : this.keyHash.subMap(Integer.MIN_VALUE, end).entrySet()){
                this.addKey(entry.getValue());
            }

            for(Map.Entry<Integer, String> entry : this.keyHash.subMap(start, Integer.MAX_VALUE).entrySet()){
                this.addKey(entry.getValue());
            }
        }
    }

    public void addKey(String key)
    {
        int hash = this.getHash(key);
        //populate key hash id to key map
        this.keyHash.put(hash, key);

        //logging
        System.out.println("hash for key : "+key+" is : "+hash);

        //populate key to server hash
        Map.Entry<Integer, String> ceilingEntry = this.serverHash.ceilingEntry(hash);
        if(ceilingEntry != null){
            this.keyToServerMap.put(key, ceilingEntry.getValue());
        }else {
            this.keyToServerMap.put(key, this.serverHash.firstEntry().getValue());
        }
    }

    private int getHash(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        hash &= 0x7fffffff;
        return hash % RING_LIMIT;
    }

    public void print(){
        System.out.println(this.keyToServerMap);
    }

    public static void main(String[] args){
        ConsistentHashManager consistentHashManager = new ConsistentHashManager();


        //add server
        consistentHashManager.addServer("server1");
        consistentHashManager.addServer("server2");
        consistentHashManager.addServer("server3");
        consistentHashManager.addServer("server4");

        //add key
        consistentHashManager.addKey("key1");
        consistentHashManager.addKey("key2");
        consistentHashManager.addKey("key3");
        consistentHashManager.addKey("key4");

        consistentHashManager.print();

        //add server
        consistentHashManager.addServer("server5");

        consistentHashManager.print();

        //remove server
        consistentHashManager.removeServer("server3");

        consistentHashManager.print();

        //remove server
        consistentHashManager.removeServer("server1");

        consistentHashManager.print();
    }
}
