package lld.elevatorsystem.strategy.impl;

import lld.elevatorsystem.model.Elevator;
import lld.elevatorsystem.model.Request;
import lld.elevatorsystem.strategy.ElevatorSelectionStrategy;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        return null;
    }
}
