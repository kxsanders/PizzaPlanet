package org.example;

public class GarlicKnots extends Product {
    private int quantity;

    public GarlicKnots(String name, String size, double basePrice, int quantity) {
        super(name, size, basePrice);
        this.quantity = quantity;
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
