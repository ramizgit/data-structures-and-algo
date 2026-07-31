package lld.parkinglot.service;

import lld.parkinglot.model.ParkingSpot;
import lld.parkinglot.model.Ticket;
import lld.parkinglot.model.Vehicle;
import lld.parkinglot.strategy.ParkingStrategy;

public class ParkingService {

    //It should coordinate the entire parking process.

    private ParkingStrategy strategy;
    private TicketService ticketService;

    public ParkingService(ParkingStrategy strategy, TicketService ticketService) {
        this.strategy = strategy;
        this.ticketService = ticketService;
    }

    public Ticket parkVehicle(Vehicle vehicle)
    {
        ParkingSpot spot = strategy.findParkingSpot(vehicle);

        if (spot == null) {
            return null; //throw new ParkingLotFullException(); //todo throw exception
        }

        spot.parkVehicle(vehicle);   // <-- Missing step

        return ticketService.createTicket(spot);
    }

    public void unparkVehicle(int ticketId)
    {
        Ticket ticket = ticketService.getTicket(ticketId);

        ParkingSpot spot = ticket.getParkingSpot();

        spot.unParkVehicle();

        ticketService.closeTicket(ticketId);
    }
}
