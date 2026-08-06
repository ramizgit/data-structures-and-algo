package lld.bookmyshow.model;

import lld.bookmyshow.enums.SeatStatus;

import java.time.LocalDateTime;

/*
 * Represents a seat for a specific show.
 *
 * A Seat is a physical entity (e.g., A1, B2) and is shared across all shows
 * running on the same screen. However, its availability, lock status, and
 * pricing are different for each show.
 *
 * Example:
 *
 * Screen 1
 *   Seat A1
 *
 * 10:00 AM Show -> AVAILABLE, ₹250
 * 07:00 PM Show -> BOOKED, ₹500
 *
 * Therefore, show-specific state is modeled separately in ShowSeat instead of
 * storing it inside Seat.
 */

public class ShowSeat {

    private Seat seat;
    private SeatStatus status;
    private LocalDateTime lockedUntil;
    private double price;

    public ShowSeat(Seat seat, SeatStatus status, double price) {
        this.seat = seat;
        this.status = status;
        this.price = price;
    }

    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    public void lock() {
        this.status = SeatStatus.LOCKED;
        this.lockedUntil = LocalDateTime.now().plusMinutes(5);
    }

    public void book() {
        this.status = SeatStatus.BOOKED;
        this.lockedUntil = null;
    }

    public void release() {
        this.status = SeatStatus.AVAILABLE;
        this.lockedUntil = null;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public LocalDateTime getLockedUntil() {
        return lockedUntil;
    }

    public void setLockedUntil(LocalDateTime lockedUntil) {
        this.lockedUntil = lockedUntil;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
