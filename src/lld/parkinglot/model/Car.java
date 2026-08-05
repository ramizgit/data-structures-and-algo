package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class Car extends Vehicle{

    public Car(String vehicleNumber) {
        this(vehicleNumber, Type.CAR);
    }

    protected Car(String vehicleNumber, Type vehicleType) {
        super(vehicleNumber, Type.CAR);
    }
}
