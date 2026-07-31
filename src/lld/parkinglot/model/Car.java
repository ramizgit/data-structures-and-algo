package lld.parkinglot.model;

import lld.parkinglot.enums.Type;

public class Car extends Vehicle{

    protected Car(String vehicleNumber, Type vehicleType) {
        super(vehicleNumber, Type.CAR);
    }
}
