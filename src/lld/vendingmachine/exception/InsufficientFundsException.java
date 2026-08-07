package lld.vendingmachine.exception;

public class InsufficientFundsException extends Exception{

    public InsufficientFundsException(){
        super("Fund is not enough to purchase this product.");
    }
}
