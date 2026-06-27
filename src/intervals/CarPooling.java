package intervals;

import java.util.*;

public class CarPooling {

    /*
    There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
    You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that
    the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are from i and toi respectively.
     The locations are given as the number of kilometers due east from the car's initial location.
    Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
     */

    //Time : O(n)
    public boolean carPooling(int[][] trips, int capacity)
    {
        //diff array
        int[] diff = new int[1001]; //important : the difference array should be indexed by location, not by trip index.

        for(int[] trip : trips){
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            diff[from] += passengers; //pickup at from
            diff[to] -= passengers; //drop at to
        }

        //prefix sum
        int currentPassengers = 0;

        for(int i=0; i<diff.length; i++){
            currentPassengers += diff[i];

            if(currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }

    //Time : O(n log n)
    public boolean carPoolingViaHeapMethod(int[][] trips, int capacity)
    {
        //sort input trips by start location
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        PriorityQueue<int[]> minheap = new PriorityQueue<>( (a, b) -> a[2] - b[2] ); //always process earliest finished location first
        int currentPassengers = 0;

        for(int[] trip : trips){
            int passengers = trip[0];
            int from = trip[1];

            //remove finished trips before adding new one
            while(!minheap.isEmpty() && minheap.peek()[2] <= from){
                currentPassengers -= minheap.poll()[0]; //offboard passengers
            }

            currentPassengers += passengers; //pick up current passengers

            //early exit
            if(currentPassengers > capacity){
                return false;
            }

            minheap.offer(trip); //add current trip to the heap
        }

        return true;
    }
}
