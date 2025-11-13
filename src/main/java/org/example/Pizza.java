package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class Pizza extends Product {

    private CrustType crustType;
    private boolean stuffedCrust;
    private List<Topping> toppings;

    public Pizza(String name, Size size, double basePrice,
                 CrustType crustType, boolean stuffedCrust, List<Topping> toppings) {
        super(name, size, basePrice);
        this.crustType = crustType;
        this.stuffedCrust = stuffedCrust;
        this.toppings = toppings;
    }

    public CrustType getCrustType() {
        return crustType;
    }

    public boolean isStuffedCrust() {
        return stuffedCrust;
    }

    public void setStuffedCrust(boolean stuffedCrust) {
        this.stuffedCrust = stuffedCrust;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(String name) {
        toppings.removeIf(t -> t.getName().equalsIgnoreCase(name));
    }


    @Override
    public double calculatePrice() {
        double total = getBasePrice();

        for (Topping t : toppings) {
            total += t.getPrice();     // <-- no size arg now
        }

        // optional stuffed crust upcharge
        if (stuffedCrust) {
            total += 2.00;
        }

        return total;
    }

    @Override
    public String toString() {
        String toppingText = toppings.isEmpty()
                ? "   (no toppings)\n"
                : toppings.stream()
                .map(Topping::toString)
                .collect(Collectors.joining("\n   ", "   ", "\n"));

        return String.format("%s (%s, %s crust)%n%s   Stuffed crust: %s%n   Item Total: $%.2f",
                getName(),
                getSize(),
                crustType,
                toppingText,
                stuffedCrust ? "Yes" : "No",
                calculatePrice());
    }
}

