package array;

import java.util.*;

public class InsertDeleteGetrandomO1 {

    //https://leetcode.com/problems/insert-delete-getrandom-o1/description/

    /*
    insert    O(1)
    remove    O(1)
    getRandom O(1)
    space     O(n)
     */

    class RandomizedSet {

        //supporting datastructures
        List<Integer> values; //to maintain set elements
        Map<Integer, Integer> valueToIdxMap; //to maintain value to index map
        private Random random;

        public RandomizedSet() {
            this.values = new ArrayList<>();
            this.valueToIdxMap = new HashMap<>();
            this.random = new Random();
        }

        public boolean insert(int val) {

            if(this.valueToIdxMap.containsKey(val)){
                return false;
            }

            this.values.add(val); //add to the end of values list - O(1)
            this.valueToIdxMap.put(val, this.values.size() - 1); //populate index map

            return true;
        }

        public boolean remove(int val) {

            if(!this.valueToIdxMap.containsKey(val)){
                return false;
            }

            /*
            1. Find index of val
            2. Get last element
            3. Move last element into index
            4. Update last element's index in map
            5. Remove val from map
            6. Remove last element from list
             */

            int index = this.valueToIdxMap.get(val);
            int lastValue = this.values.getLast();

            if (index != values.size() - 1) {
                values.set(index, lastValue);
                valueToIdxMap.put(lastValue, index);
            }

            this.values.removeLast();
            this.valueToIdxMap.remove(val);

            return true;
        }

        public int getRandom() {
            int random = this.random.nextInt(this.values.size());
            return this.values.get(random);
        }
    }
}
