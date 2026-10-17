package binarysearch.historysearch;

import java.util.*;

public class SnapshotArray {

    //https://leetcode.com/problems/snapshot-array/description/

    List<VersionedValue>[] arr;
    int currSnap = 0;

    public SnapshotArray(int length) {
        this.arr = new ArrayList[length];

        for (int i = 0; i < length; i++) {
            arr[i] = new ArrayList<>();
            arr[i].add(new VersionedValue(0, 0));
        }
    }

    //O(1)
    public void set(int index, int val) {

        /*
        Same snapshot ID → overwrite the latest entry.
        New snapshot ID → append a new entry.
         */

        List<VersionedValue> list = arr[index];

        if(list.getLast().snapId == currSnap){
            list.getLast().value = val; //override
        }else{
            list.add(new VersionedValue(currSnap, val)); //append
        }
    }

    //O(1)
    public int snap() {
        return currSnap++; //freeze the current snapshot, return its ID, then advance to the next snapshot.
    }

    //O(log k) where k is the number of updates for that index.
    public int get(int index, int snap_id) {

        List<VersionedValue> list = arr[index];

        //binary search on list - find the latest snapshot whose snapId <= requested snap_id
        int low = 0;
        int high = list.size()-1;

        int answer = 0;

        while(low <= high){

            int mid = low + (high - low)/2;

            if(list.get(mid).snapId <= snap_id){
                answer = mid; //possible answer
                low = mid + 1; //try higher in case this value was overridden in a later snapshot
            }else{
                high = mid - 1; //try lower snapshot
            }
        }

        return list.get(answer).value;
    }

    static class VersionedValue {
        int snapId;
        int value;

        VersionedValue(int snapId, int value) {
            this.snapId = snapId;
            this.value = value;
        }
    }
}
