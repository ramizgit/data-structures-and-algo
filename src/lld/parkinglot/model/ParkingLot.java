package lld.parkinglot.model;

import java.util.List;

public class ParkingLot {

    private String parkingLotId;
    private List<ParkingSpot> parkingSpots;

    public ParkingLot(String parkingLotId, List<ParkingSpot> parkingSpots) {
        this.parkingLotId = parkingLotId;
        this.parkingSpots = parkingSpots;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }
}
