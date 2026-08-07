package lld.vendingmachine.exception;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(){
        super("oops ! Product not found.");
    }
}
