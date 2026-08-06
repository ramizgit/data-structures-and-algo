package lld.elevatorsystem.strategy.impl;

import lld.elevatorsystem.model.Elevator;
import lld.elevatorsystem.model.Request;
import lld.elevatorsystem.strategy.ElevatorSelectionStrategy;

import java.util.List;

public class LeastBusyElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {

        //for this we need maintain queue of request in Elevator model: private Queue<Request> pendingRequests = new LinkedList<>();
        /*
        if (elevators == null || elevators.isEmpty()) {
            return null;
        }

        Elevator leastBusy = null;
        int minRequests = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {

            if (elevator.getPendingRequestCount() < minRequests) {

                minRequests = elevator.getPendingRequestCount();
                leastBusy = elevator;
            }
        }

        return leastBusy;
         */

        return null;
    }
}
