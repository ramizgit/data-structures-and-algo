package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class CarSpot extends ParkingSpot{
    public CarSpot(int spotId, boolean isOccupied, Vehicle vehicle) {
        super(spotId, Type.CAR, isOccupied, vehicle);
    }
}
