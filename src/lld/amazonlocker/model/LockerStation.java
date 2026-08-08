package lld.amazonlocker.model;

import java.util.List;

//actual group of lockers at one physical location
public class LockerStation {

    private int stationId;
    private String location;
    private List<Locker> lockers;


    public LockerStation(int stationId, String location, List<Locker> lockers) {
        this.stationId = stationId;
        this.location = location;
        this.lockers = lockers;
    }

    public int getStationId() {
        return stationId;
    }

    public String getLocation() {
        return location;
    }

    public List<Locker> getLockers() {
        return lockers;
    }
}
