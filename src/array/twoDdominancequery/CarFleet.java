package arrays.twoDdominancequery;

import java.util.*;

public class CarFleet {

    //https://leetcode.com/problems/car-fleet/description/

    public int carFleet(int target, int[] position, int[] speed)
    {
        //target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]

        //calculate time to reach for each car, and create list of car pair: {position, timeToReach}
        List<double[]> cars = new ArrayList<>(); //list of cars pair: {position, timeToReach}

        for(int i=0; i<position.length; i++){
            int distance = target - position[i];
            double time = (double) distance / speed[i];

            cars.add(new double[]{position[i], time});
        }

        //sort by start position in descending order
        cars.sort( (a, b) -> (int) (b[0] - a[0]));

        //traverse list and keep track of prev max time, new fleet only when curr time is higher than prev max time
        double maxTime = 0;
        int fleet = 0;

        for(int i=0; i<cars.size(); i++){
            double currTime = cars.get(i)[1];
            if(currTime > maxTime){
                fleet++; //new fleet
                maxTime = currTime; //reset max time
            }
        }

        return fleet;
    }
}
