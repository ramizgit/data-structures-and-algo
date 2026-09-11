package lld.bookmyshow.model;

import lld.bookmyshow.enums.BookingStatus;

import java.util.List;

public class Booking {

    int id;
    User user;
    Show show;
    private List<ShowSeat> seats;
    private BookingStatus bookingStatus; //?

    /*
    If payment succeeds:

    Booking
    Seats: A1, A2, A3
    Status: CONFIRMED

    If the booking is cancelled:

    Booking
    Seats: A1, A2, A3
    Status: CANCELLED
     */

    public Booking(int id, User user, Show show, List<ShowSeat> seats) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
