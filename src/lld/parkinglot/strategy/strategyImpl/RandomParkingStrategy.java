package lld.parkinglot.strategy.strategyImpl;

import lld.parkinglot.model.ParkingSpot;
import lld.parkinglot.model.Vehicle;
import lld.parkinglot.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomParkingStrategy implements ParkingStrategy {

    private final List<ParkingSpot> parkingSpots;
    private final Random random = new Random();

    public RandomParkingStrategy(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    @Override
    public ParkingSpot findParkingSpot(Vehicle vehicle) {

        List<ParkingSpot> availableSpots = new ArrayList<>();

        for(ParkingSpot spot : parkingSpots){
            if(!spot.isOccupied() && spot.getSpotType() == vehicle.getVehicleType()){
                availableSpots.add(spot);
            }
        }

        if(availableSpots.isEmpty()){
            return null;
        }

        int randomIdx = random.nextInt(availableSpots.size());

        return availableSpots.get(randomIdx);
    }
}
