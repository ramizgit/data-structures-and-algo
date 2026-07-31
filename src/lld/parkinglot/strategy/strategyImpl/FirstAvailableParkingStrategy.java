package lld.parkinglot.strategy.strategyImpl;

import lld.parkinglot.model.ParkingSpot;
import lld.parkinglot.model.Vehicle;
import lld.parkinglot.strategy.ParkingStrategy;

import java.util.List;

public class FirstAvailableParkingStrategy implements ParkingStrategy {

    private final List<ParkingSpot> parkingSpots;

    public FirstAvailableParkingStrategy(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    @Override
    public ParkingSpot findParkingSpot(Vehicle vehicle) {

        for(ParkingSpot spot : parkingSpots){
            if(!spot.isOccupied() && spot.getSpotType() == vehicle.getVehicleType()){
                return spot;
            }
        }

        return null;
    }
}
