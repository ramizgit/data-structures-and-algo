package lld.elevatorsystem.model;

import lld.elevatorsystem.enums.Direction;

public class Request {

    private int floor;
    private Direction direction; //primarily for External requests where destination is not known yet

    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
