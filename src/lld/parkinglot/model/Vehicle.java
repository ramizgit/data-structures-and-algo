package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public abstract class Vehicle {

    private final String vehicleNumber;
    private final Type vehicleType;

    protected Vehicle(String vehicleNumber, Type vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Type getVehicleType() {
        return vehicleType;
    }
}
