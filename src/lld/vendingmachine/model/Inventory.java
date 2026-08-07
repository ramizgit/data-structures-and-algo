package lld.vendingmachine.model;

//represents a product and its quantity.
public class Inventory {

    private Product product;
    private int quantity;

    public Inventory(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public boolean hasStock(int quantity){
        return this.quantity >= quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void dispense(int quantity){
        this.quantity -= quantity;
    }

    //public void refill(int quantity);   // follow-up
}
