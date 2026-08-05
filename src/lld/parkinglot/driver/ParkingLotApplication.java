package lld.parkinglot.driver;

import lld.parkinglot.enums.Type;
import lld.parkinglot.model.*;
import lld.parkinglot.service.ParkingService;
import lld.parkinglot.service.TicketService;
import lld.parkinglot.strategy.ParkingStrategy;
import lld.parkinglot.strategy.strategyImpl.FirstAvailableParkingStrategy;

import java.util.ArrayList;
import java.util.List;

/*
Flow :-
Parking Spots
      |
      v
ParkingLot
      |
      v
ParkingStrategy
      |
      v
TicketService
      |
      v
ParkingService
      |
      v
parkVehicle()

unparkVehicle()
 */
public class ParkingLotApplication {

    public static void main(String[] args)
    {
        //Step 1: Create Parking Spots
        List<ParkingSpot> parkingSpots = new ArrayList<>();

        parkingSpots.add(new ParkingSpot(1, Type.CAR));
        parkingSpots.add(new ParkingSpot(2, Type.CAR));
        parkingSpots.add(new ParkingSpot(3, Type.BIKE));
        parkingSpots.add(new ParkingSpot(4, Type.BIKE));

        //Step 2: Create Parking Lot
        ParkingLot parkingLot = new ParkingLot("PVR Mall", parkingSpots);

        //Step 3: Choose Parking Strategy
        ParkingStrategy parkingStrategy = new FirstAvailableParkingStrategy(parkingLot.getParkingSpots());

        //Step 4: Create Services
        TicketService ticketService = new TicketService();

        ParkingService parkingService = new ParkingService(parkingStrategy, ticketService);

        //Step 5: Create Vehicles
        Vehicle car1 = new Car("KA01AB1234");

        Vehicle bike1 = new Bike("KA05XY9876");

        //Step 6: Park Vehicles
        Ticket ticket1 = parkingService.parkVehicle(car1);
        Ticket ticket2 = parkingService.parkVehicle(bike1);

        //Step 7: Unpark
        parkingService.unparkVehicle(ticket1.getTicketId());
    }
}
