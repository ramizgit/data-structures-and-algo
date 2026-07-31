package lld.parkinglot.strategy;

import lld.parkinglot.model.ParkingSpot;
import lld.parkinglot.model.Vehicle;

public interface ParkingStrategy {

    //given a vehicle, tell me which spot should be used
    ParkingSpot findParkingSpot(Vehicle vehicle);
}
