package array.twoDdominancequery;

import java.util.*;

public class CarFleet {

    //https://leetcode.com/problems/car-fleet/description/

    public int carFleet(int target, int[] position, int[] speed)
    {
        //target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]

        //calculate time to reach for each car, and create list of car pair: {position, timeToReach}
        List<Car> cars = new ArrayList<>(); //list of cars pair: {position, timeToReach}

        for(int i=0; i<position.length; i++){
            int distance = target - position[i];
            double time = (double) distance / speed[i];

            cars.add(new Car(position[i], time));
        }

        //sort by start position in descending order
        cars.sort( (a, b) -> Integer.compare(b.position, a.position));

        //traverse list and keep track of prev max time, new fleet only when curr time is higher than prev max time
        double maxTimeSoFar = 0;
        int fleet = 0;

        for (Car car : cars) {
            if (car.time > maxTimeSoFar) {
                //reaches the target later than all fleets ahead, so it starts a new fleet.
                fleet++; //new fleet
                maxTimeSoFar = car.time; //reset max time to current max
            }
        }

        return fleet;
    }

    static class Car {
        int position;
        double time;

        public Car(int position, double time) {
            this.position = position;
            this.time = time;
        }
    }
}
