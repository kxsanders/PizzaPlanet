package org.example;

public class Drink extends Product {
    public String flavor;

    public Drink(String name, String size, double basePrice, String flavor) {
        super(name, size, basePrice);
        this.flavor = flavor;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }

    @Override
    public String toString() {
        return getName() + " (" + flavor + ", " + getSize() + ") - $" + calculatePrice();
    }
}
