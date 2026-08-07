package lld.vendingmachine.exception;

public class OutOfStockException extends Exception{

    public OutOfStockException(){
        super("Sorry! product is out of stock.");
    }
}
