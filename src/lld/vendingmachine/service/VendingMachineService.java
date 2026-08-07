package lld.vendingmachine.service;

/*
Responsibilities:-
    Select product
    Accept payment
    Check inventory
    Dispense product
    Return change
 */

/*
Flow:-
Select Product
        ↓
Check Product Exists
        ↓
Check Stock
        ↓
Accept Money
        ↓
Enough Money?
        ↓
Dispense Product
        ↓
Reduce Inventory
        ↓
Return Change
        ↓
Create Transaction
 */

import lld.vendingmachine.enums.TransactionStatus;
import lld.vendingmachine.exception.InsufficientFundsException;
import lld.vendingmachine.exception.OutOfStockException;
import lld.vendingmachine.exception.ProductNotFoundException;
import lld.vendingmachine.model.*;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineService {

    private final VendingMachine vendingMachine;
    private int transactionId;

    private final Map<Integer, Transaction> transactionHistory;

    public VendingMachineService(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.transactionHistory = new HashMap<>();
        this.transactionId = 1;
    }

    public Transaction purchase(PurchaseRequest request) throws ProductNotFoundException, OutOfStockException, InsufficientFundsException {

        //find inventory
        Inventory inventory = vendingMachine.getInventory().get(request.getProductId());

        //quantity validation
        if (request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }

        //check if product exits
        if(inventory == null){
            throw new ProductNotFoundException();
        }

        //check if out of stock
        if(!inventory.hasStock(request.getQuantity())){
            throw new OutOfStockException();
        }

        //payment validation
        Product product = inventory.getProduct();
        double totalPrice = product.getPrice() * request.getQuantity();

        if (request.getAmountPaid() < totalPrice) {
            throw new InsufficientFundsException();
        }

        //dispense
        inventory.dispense(request.getQuantity());

        //return change
        double change = request.getAmountPaid() - totalPrice;

        //create transaction and store for audit
        Transaction transaction = new Transaction(transactionId++, product, request.getQuantity(), request.getAmountPaid(), change, TransactionStatus.SUCCESS);
        transactionHistory.put(transaction.getId(), transaction);

        return transaction;
    }

    public Transaction getTransaction(int transactionId) {
        return this.transactionHistory.get(transactionId);
    }
}
