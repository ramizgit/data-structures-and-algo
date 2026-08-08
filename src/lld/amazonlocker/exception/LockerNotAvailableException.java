package lld.amazonlocker.exception;

public class LockerNotAvailableException extends RuntimeException {

    public LockerNotAvailableException() {
        super("No suitable locker available");
    }
}
