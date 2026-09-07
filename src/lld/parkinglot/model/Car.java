package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class Car extends Vehicle{

    public Car(String vehicleNumber) {
        super(vehicleNumber, Type.CAR);
    }
}
