package org.example;

public abstract class Product {
private String productName;
private Size size;
private double basePrice;

    public Product(String name, Size size, double basePrice) {
        this.productName = name;
        this.size = size;
        this.basePrice = basePrice;
    }

    public String getName() {
        return productName;
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
        return productName + " (" + size + ") - $" + basePrice;
    }
}
