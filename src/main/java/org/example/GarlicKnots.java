package org.example;

public class GarlicKnots extends Product {
    private int quantity;

    public GarlicKnots(String name, Size size, double basePrice, int quantity) {
        super(name, size, basePrice);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice() * quantity;
    }

    @Override
    public String toString() {
        return quantity + "x " + getName() + " - $" + calculatePrice();
    }
}
