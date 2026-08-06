package lld.elevatorsystem.driver;

/*
ElevatorApplication

↓

Create Elevator

↓

Create ElevatorService

↓

Request floor

↓

Move Elevator
 */

import lld.elevatorsystem.enums.Direction;
import lld.elevatorsystem.enums.ElevatorStatus;
import lld.elevatorsystem.model.Elevator;
import lld.elevatorsystem.model.Request;
import lld.elevatorsystem.service.ElevatorService;
import lld.elevatorsystem.strategy.impl.NearestElevatorStrategy;

import java.util.List;

public class ElevatorApplication {

    public static void main(String[] args)
    {
        Elevator elevator1 = new Elevator(1, 0, Direction.UP, ElevatorStatus.IDLE);
        Elevator elevator2 = new Elevator(2, 5, Direction.UP, ElevatorStatus.IDLE);
        Elevator elevator3 = new Elevator(3, 10, Direction.UP, ElevatorStatus.IDLE);

        List<Elevator> elevators = List.of(elevator1, elevator2, elevator3);

        ElevatorService elevatorService = new ElevatorService(elevators, new NearestElevatorStrategy());

        elevatorService.requestElevator(new Request(5, Direction.UP));

        elevatorService.requestElevator(new Request(2, Direction.DOWN));
    }
}
