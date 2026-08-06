package lld.bookmyshow.driver;

import lld.bookmyshow.enums.SeatStatus;
import lld.bookmyshow.enums.SeatType;
import lld.bookmyshow.exception.PaymentFailedException;
import lld.bookmyshow.exception.SeatUnavailableException;
import lld.bookmyshow.model.*;
import lld.bookmyshow.service.BookingService;
import lld.bookmyshow.service.PaymentService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieBookingApplication {

    public static void main(String[] args) throws SeatUnavailableException, PaymentFailedException {
        //create movie
        Movie movie = new Movie(1, "Interstellar", 169, "English");

        //create seats
        Seat a1 = new Seat(1,"A1", SeatType.SILVER);
        Seat a2 = new Seat(2,"A2", SeatType.SILVER);
        Seat b1 = new Seat(3,"B1", SeatType.GOLD);

        //create screen
        Screen screen = new Screen(1, List.of(a1, a2, b1));

        //create theatre
        Theatre theatre = new Theatre(1, "PVR", "Bangalore", "Whitefield", List.of(screen));

        //Create ShowSeats
        Map<String, ShowSeat> showSeats = new HashMap<>();
        showSeats.put("A1", new ShowSeat(a1, SeatStatus.AVAILABLE, 250));
        showSeats.put("A2", new ShowSeat(a2, SeatStatus.AVAILABLE, 250));
        showSeats.put("B1", new ShowSeat(b1, SeatStatus.AVAILABLE, 500));

        //create show
        Show show = new Show(1, movie, screen, LocalDateTime.now(), showSeats);

        //create user
        User user = new User(1, "Alice");

        //create services
        PaymentService paymentService = new PaymentService();
        BookingService bookingService = new BookingService(paymentService);

        //book tickets
        Booking booking = bookingService.bookTickets(user, show, List.of("A1", "A2"));

        /*
        What remains?
        Only one major discussion remains, and it's usually the last question in a BookMyShow interview:
        How do you prevent two users from booking the same seat simultaneously?
        That opens the discussion on:

            synchronized
            Optimistic locking
            Pessimistic locking
            Database transactions
            Redis/distributed locks
         */
    }
}
