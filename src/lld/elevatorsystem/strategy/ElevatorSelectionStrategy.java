package lld.elevatorsystem.strategy;

import lld.elevatorsystem.model.Elevator;
import lld.elevatorsystem.model.Request;

import java.util.List;

public interface ElevatorSelectionStrategy {

    Elevator selectElevator(List<Elevator> elevators, Request request);
}
