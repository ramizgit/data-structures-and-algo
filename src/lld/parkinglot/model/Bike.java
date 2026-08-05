package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class Bike extends Vehicle{

    public Bike(String vehicleNumber) {
        this(vehicleNumber, Type.BIKE);
    }

    protected Bike(String vehicleNumber, Type vehicleType) {
        super(vehicleNumber, Type.BIKE);
    }
}
