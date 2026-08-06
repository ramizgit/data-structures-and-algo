package lld.bookmyshow.model;

import java.util.List;

public class Booking {

    int id;
    User user;
    Show show;
    private List<ShowSeat> seats;
    int bookingStatus; //?

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

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<ShowSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<ShowSeat> seats) {
        this.seats = seats;
    }
}
