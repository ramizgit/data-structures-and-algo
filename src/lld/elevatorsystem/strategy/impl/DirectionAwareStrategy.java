package lld.elevatorsystem.strategy.impl;

import lld.elevatorsystem.enums.Direction;
import lld.elevatorsystem.model.Elevator;
import lld.elevatorsystem.model.Request;
import lld.elevatorsystem.strategy.ElevatorSelectionStrategy;

import java.util.List;

//prefers elevators already moving in the requested direction
public class DirectionAwareStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {

        if (elevators == null || elevators.isEmpty()) {
            return null;
        }

        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {

            boolean sameDirection = elevator.getDirection() == request.getDirection();

            boolean willPassFloor =
                    (request.getDirection() == Direction.UP && elevator.getCurrentFloor() <= request.getFloor())
                            || (request.getDirection() == Direction.DOWN && elevator.getCurrentFloor() >= request.getFloor());

            if (sameDirection && willPassFloor) {

                int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());

                if (distance < minDistance) {
                    minDistance = distance;
                    best = elevator;
                }
            }
        }

        // fallback to NearestElevatorStrategy if no best found
        if (best == null) {
            return new NearestElevatorStrategy().selectElevator(elevators, request);
        }

        return best;
    }
}
