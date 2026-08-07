package lld.vendingmachine.model;

public class PurchaseRequest {

    private int productId;
    private int quantity;
    private double amountPaid;

    public PurchaseRequest(int productId, int quantity, double amountPaid) {
        this.productId = productId;
        this.quantity = quantity;
        this.amountPaid = amountPaid;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}
