package lld.vendingmachine.model;

import java.util.Map;

public class VendingMachine {

    private int id;
    private Map<Integer, Inventory> inventory; //{productId : Inventory}

    public VendingMachine(int id, Map<Integer, Inventory> inventory) {
        this.id = id;
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Integer, Inventory> inventory) {
        this.inventory = inventory;
    }
}
