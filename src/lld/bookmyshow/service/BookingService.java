package lld.bookmyshow.service;

import lld.bookmyshow.exception.PaymentFailedException;
import lld.bookmyshow.exception.SeatUnavailableException;
import lld.bookmyshow.model.Booking;
import lld.bookmyshow.model.Show;
import lld.bookmyshow.model.ShowSeat;
import lld.bookmyshow.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
bookTickets()

↓

Find requested ShowSeats

↓

If any unavailable

throw exception

↓

Lock all seats

↓

Payment

↓

Success?

↓

Book seats

↓

Create Booking

↓

Return Booking
 */

public class BookingService {

    private int bookingId = 1;
    private Map<Integer, Booking> bookings = new HashMap<>();
    private PaymentService paymentService;

    public BookingService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Booking bookTickets(User user, Show show, List<String> seatNumbers) throws SeatUnavailableException, PaymentFailedException {

        //step1 : Find seats
        List<ShowSeat> selectedSeats = new ArrayList<>();

        for (String seatNumber : seatNumbers) {
            ShowSeat seat = show.getShowSeats().get(seatNumber);

            if (seat == null) {
                throw new IllegalArgumentException("Invalid seat number");
            }

            selectedSeats.add(seat);
        }

        //step2 : Verify availability
        for (ShowSeat seat : selectedSeats) {
            if (!seat.isAvailable()) {
                throw new SeatUnavailableException();
            }
        }

        //step3 : lock
        for (ShowSeat seat : selectedSeats) {
            seat.lock();
        }

        //step4 : payment
        double amount = 0;

        for (ShowSeat seat : selectedSeats) {
            amount += seat.getPrice();
        }

        boolean paymentSuccessful = paymentService.pay(user, amount);

        if (paymentSuccessful) {
            //confirm seat
            for (ShowSeat seat : selectedSeats) {
                seat.book();
            }

            //create booking
            Booking booking = new Booking(bookingId++, user, show, selectedSeats);

            bookings.put(booking.getId(), booking);
            return booking;
        } else {
            //release seats
            for (ShowSeat seat : selectedSeats) {
                seat.release();
            }

            throw new PaymentFailedException();
        }
    }
}
