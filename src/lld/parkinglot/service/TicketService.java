package lld.parkinglot.service;

import lld.parkinglot.model.ParkingSpot;
import lld.parkinglot.model.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketService {

    private int id;
    private int time;
    private Map<Integer, Ticket> tickets;

    public TicketService()
    {
        this.id = 1;
        this.time = 1;
        this.tickets = new HashMap<>();
    }

    public Ticket createTicket(ParkingSpot parkingSpot)
    {
        Ticket ticket = new Ticket(id, parkingSpot, time);
        this.tickets.put(id, ticket);

        id++;
        time++;

        return ticket;
    }

    public void closeTicket(int ticketId) {
        tickets.remove(ticketId);
    }

    public Ticket getTicket(int ticketId) {
        return this.tickets.get(ticketId);
    }
}
