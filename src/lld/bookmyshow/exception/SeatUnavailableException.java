package lld.bookmyshow.exception;

public class SeatUnavailableException extends Exception{

    public SeatUnavailableException(){
        super("Oops ! Seat is not available.");
    }
}
