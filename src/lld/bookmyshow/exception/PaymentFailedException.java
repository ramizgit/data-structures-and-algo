package lld.bookmyshow.exception;

public class PaymentFailedException extends Exception{

    public PaymentFailedException(){
        super("Oops! Pyament failed");
    }
}
