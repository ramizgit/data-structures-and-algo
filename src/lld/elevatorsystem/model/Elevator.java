package lld.elevatorsystem.model;

import lld.elevatorsystem.enums.Direction;
import lld.elevatorsystem.enums.ElevatorStatus;

import static lld.elevatorsystem.enums.Direction.UP;

public class Elevator {

    private int elevatorId;          // Unique elevator identifier

    private int currentFloor;        // Current location

    private Direction direction;     // UP or DOWN while moving

    private ElevatorStatus status;   // IDLE or MOVING

    public Elevator(int elevatorId, int currentFloor, Direction direction, ElevatorStatus status) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.status = status;
    }

    public void moveUp() {
        currentFloor++;
    }

    public void moveDown() {
        currentFloor--;
    }

    public void updateDirection(int destinationFloor) {
        if (destinationFloor > currentFloor) {
            direction = Direction.UP;
        } else if (destinationFloor < currentFloor) {
            direction = Direction.DOWN;
        }
    }

    public void moveToFloor(int destinationFloor) {

        //edge case- already at destination floor
        if (currentFloor == destinationFloor) {
            return; //no action taken, simply return
        }

        //start move
        this.status = ElevatorStatus.MOVING;

        while (currentFloor != destinationFloor) {

            if (direction == UP) {
                this.moveUp();
            } else {
                this.moveDown();
            }

            System.out.println("Elevator " + elevatorId + " passing floor " + currentFloor);
        }

        //stop
        this.status = ElevatorStatus.IDLE;
    }

    //-------------setters and getters
    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorStatus getStatus() {
        return status;
    }
}
