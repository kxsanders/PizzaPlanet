package org.example;

import org.example.EnumClasses.DrinkFlavor;
import org.example.EnumClasses.Size;

public class Drink extends Product {
    public DrinkFlavor flavor;

    public Drink(String name, Size size, double basePrice, DrinkFlavor flavor) {
        super(name, size, basePrice);
        this.flavor = flavor;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }

    @Override
    public String toString() {
        return getName() + " [" + flavor + ", " + getSize().drinkLabel()
                + "] - $" + String.format("%.2f", calculatePrice());
    }
}
