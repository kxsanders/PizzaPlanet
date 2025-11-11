package org.example;

public class GarlicKnots extends Product {
    private int quantity;
    private static final double UNIT_PRICE = 1.50;

    public GarlicKnots(int quantity) {
        // Product(name, size, basePrice)
        // Garlic knots have no size SOOOOO use null or a special size
        super("Garlic Knots", null, UNIT_PRICE);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public double calculatePrice() {
        return UNIT_PRICE * quantity;
    }

    @Override
    public String toString() {
        return quantity + "x Garlic Knots - $" + String.format("%.2f", calculatePrice());
    }
}
