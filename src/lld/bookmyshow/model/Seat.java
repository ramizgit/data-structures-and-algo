package lld.bookmyshow.model;

import lld.bookmyshow.enums.SeatType;

public class Seat {

    int id; //internal identifier
    String number; //this is what end users see
    SeatType seatType;

    public Seat(int id, String number, SeatType seatType) {
        this.id = id;
        this.number = number;
        this.seatType = seatType;
    }
}
