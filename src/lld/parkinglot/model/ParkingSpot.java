package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class ParkingSpot {

    private int spotId;
    private Type spotType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(int spotId, Type spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isOccupied = false;
        this.vehicle = null;
    }

    public ParkingSpot(int spotId, Type spotType, boolean isOccupied, Vehicle vehicle) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isOccupied = isOccupied;
        this.vehicle = vehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void unParkVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public int getSpotId() {
        return spotId;
    }

    public Type getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
