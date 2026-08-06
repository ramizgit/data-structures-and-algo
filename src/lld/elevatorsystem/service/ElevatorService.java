package lld.elevatorsystem.service;

/*
Responsibilities:
    Accept requests
    Move elevator
    Update current floor
    Update direction
 */

import lld.elevatorsystem.model.Elevator;
import lld.elevatorsystem.model.Request;
import lld.elevatorsystem.strategy.ElevatorSelectionStrategy;

import java.util.List;

import static lld.elevatorsystem.enums.Direction.DOWN;
import static lld.elevatorsystem.enums.Direction.UP;
import static lld.elevatorsystem.enums.ElevatorStatus.MOVING;

public class ElevatorService {

    private List<Elevator> elevators;
    private ElevatorSelectionStrategy strategy;

    public ElevatorService(List<Elevator> elevators, ElevatorSelectionStrategy strategy) {
        this.elevators = elevators;
        this.strategy = strategy;
    }

    public void requestElevator(Request request)
    {
        /*
        Flow:-
        Request arrives -> Determine direction -> Move elevator -> stop -> become idle
         */

        //Select elevator
        Elevator elevator = strategy.selectElevator(elevators, request);

        if (elevator == null) {
            throw new IllegalStateException("No elevator available.");
        }

        //update direction
        elevator.updateDirection(request.getFloor());

        //move elevator
        elevator.moveToFloor(request.getFloor());

        System.out.println("Elevator " + elevator.getElevatorId() + " reached floor " + elevator.getCurrentFloor());
    }


}
