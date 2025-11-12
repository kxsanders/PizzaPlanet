package org.example;

import org.example.EnumClasses.Size;
import org.example.EnumClasses.ToppingCategory;

public class Topping {
private String name;
private ToppingCategory category;
private boolean isPremium;
private boolean isExtra;
private double price;

    public Topping(String name, ToppingCategory category, boolean isExtra, Size size) {
        this.name = name;
        this.category = category;
        this.isExtra = isExtra;

        // auto calculate price
        this.price = PricingUtility.getToppingPrice(size, this);
    }

    public String getName() {
        return name;
    }

    public ToppingCategory getCategory() {
        return category;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + (isExtra ? " (extra)" : "") + " - $" + String.format("%.2f", price);
    }


}
