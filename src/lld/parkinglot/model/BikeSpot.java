package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class BikeSpot extends ParkingSpot{

    public BikeSpot(int spotId, boolean isOccupied, Vehicle vehicle) {
        super(spotId, Type.BIKE, isOccupied, vehicle);
    }
}
