package lld.parkinglot.model;

public class Ticket {

    private int ticketId;
    private ParkingSpot parkingSpot;
    private int entryTime;

    public Ticket(int ticketId, ParkingSpot parkingSpot, int entryTime) {
        this.ticketId = ticketId;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
    }


    public int getTicketId() {
        return ticketId;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public int getEntryTime() {
        return entryTime;
    }
}
