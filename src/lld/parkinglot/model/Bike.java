package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class Bike extends Vehicle{

    public Bike(String vehicleNumber) {
        super(vehicleNumber, Type.BIKE);
    }
}
