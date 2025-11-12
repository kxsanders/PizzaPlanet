package org.example;

public class GarlicKnots extends Product {
    private int quantity;

    public GarlicKnots(int quantity) {
        super("Garlic Knots", Size.SMALL, PricingUtility.getGarlicKnotsPrice());
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public double calculatePrice() {
        return quantity * PricingUtility.getGarlicKnotsPrice();
    }

    @Override
    public String toString() {
        return String.format("%dx %s - $%.2f", quantity, getName(), calculatePrice());
    }
}
