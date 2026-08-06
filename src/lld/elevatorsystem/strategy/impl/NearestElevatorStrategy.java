package lld.elevatorsystem.strategy.impl;

import lld.elevatorsystem.model.Elevator;
import lld.elevatorsystem.model.Request;
import lld.elevatorsystem.strategy.ElevatorSelectionStrategy;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {

        if (elevators == null || elevators.isEmpty()) {
            return null;
        }

        Elevator nearestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {

            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());

            if (distance < minDistance) {
                minDistance = distance;
                nearestElevator = elevator;
            }
        }

        return nearestElevator;
    }
}
