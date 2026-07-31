package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class Bike extends Vehicle{

    protected Bike(String vehicleNumber, Type vehicleType) {
        super(vehicleNumber, Type.BIKE);
    }
}
