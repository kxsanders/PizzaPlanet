package org.example;

public abstract class Product {
private String name;
private Size size;
private double basePrice;

    public Product(String name, Size size, double basePrice) {
        this.name = name;
        this.size = size;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public abstract double calculatePrice();

    @Override
    public String toString() {
        return name + " (" + size + ") - $" + basePrice;
    }
}
