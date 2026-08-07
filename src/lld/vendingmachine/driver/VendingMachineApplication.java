package lld.vendingmachine.driver;

/*
VendingMachineApplication

↓

Create Products

↓

Create Inventory

↓

Create Machine

↓

Create Service

↓

Purchase
 */

import lld.vendingmachine.exception.InsufficientFundsException;
import lld.vendingmachine.exception.OutOfStockException;
import lld.vendingmachine.exception.ProductNotFoundException;
import lld.vendingmachine.model.*;
import lld.vendingmachine.service.VendingMachineService;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineApplication {

    public static void main(String[] args) throws ProductNotFoundException, OutOfStockException, InsufficientFundsException {

        // Create products
        Product coke = new Product(1, "Coke", 40);
        Product pepsi = new Product(2, "Pepsi", 35);
        Product chips = new Product(3, "Lays", 20);

        // Create inventory
        Inventory cokeInventory = new Inventory(coke, 10);
        Inventory pepsiInventory = new Inventory(pepsi, 8);
        Inventory chipsInventory = new Inventory(chips, 15);

        // Populate vending machine inventory
        Map<Integer, Inventory> inventory = new HashMap<>();
        inventory.put(coke.getId(), cokeInventory);
        inventory.put(pepsi.getId(), pepsiInventory);
        inventory.put(chips.getId(), chipsInventory);

        // Create vending machine
        VendingMachine vendingMachine = new VendingMachine(1, inventory);

        // Create service
        VendingMachineService vendingMachineService = new VendingMachineService(vendingMachine);

        // Customer purchase request
        PurchaseRequest request = new PurchaseRequest(coke.getId(), 2, 100);

        // Purchase
        Transaction transaction = vendingMachineService.purchase(request);

        // Print transaction
        System.out.println("Transaction Id : " + transaction.getId());
        System.out.println("Product        : " + transaction.getProduct().getName());
        System.out.println("Quantity       : " + transaction.getQuantity());
        System.out.println("Amount Paid    : ₹" + transaction.getAmountPaid());
        System.out.println("Change         : ₹" + transaction.getChangeReturned());
        System.out.println("Status         : " + transaction.getStatus());
    }
}
