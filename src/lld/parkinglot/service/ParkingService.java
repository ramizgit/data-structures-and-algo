package lld.parkinglot.service;

import lld.parkinglot.model.ParkingSpot;
import lld.parkinglot.model.Ticket;
import lld.parkinglot.model.Vehicle;
import lld.parkinglot.strategy.ParkingStrategy;

public class ParkingService {

    //It should coordinate the entire parking process.

    private ParkingStrategy parkingStrategy;
    private TicketService ticketService;

    public ParkingService(ParkingStrategy parkingStrategy, TicketService ticketService) {
        this.parkingStrategy = parkingStrategy;
        this.ticketService = ticketService;
    }

    public Ticket parkVehicle(Vehicle vehicle)
    {
        //find spot
        ParkingSpot spot = parkingStrategy.findParkingSpot(vehicle);

        if (spot == null) {
            return null; //throw new ParkingLotFullException(); //todo throw exception
        }

        //park
        spot.parkVehicle(vehicle);

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
