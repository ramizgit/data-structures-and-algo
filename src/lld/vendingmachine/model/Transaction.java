package lld.vendingmachine.model;

import lld.vendingmachine.enums.TransactionStatus;

public class Transaction {

    private int id;
    private Product product;
    private int quantity;
    private double amountPaid;
    private double changeReturned;
    private TransactionStatus status;

    public Transaction(int id, Product product, int quantity, double amountPaid, double changeReturned, TransactionStatus status) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.amountPaid = amountPaid;
        this.changeReturned = changeReturned;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChangeReturned() {
        return changeReturned;
    }

    public TransactionStatus getStatus() {
        return status;
    }
}
