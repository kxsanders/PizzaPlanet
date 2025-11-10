package org.example;

public class Topping {
private String name;
private ToppingCategory category;
private boolean isPremium;
private boolean isExtra;
private double price;

    public Topping(String name, ToppingCategory category, boolean isPremium, boolean isExtra, double price) {
        this.name = name;
        this.category = category;
        this.isPremium = isPremium;
        this.isExtra = isExtra;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ToppingCategory getCategory() {
        return category;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + (isExtra ? " (extra)" : "") + " - $" + price;
    }


}
