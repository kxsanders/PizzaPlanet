package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza extends Product {
    private CrustType crustType;
    private boolean stuffedCrust;
    private List<Topping> toppings;

    public Pizza(String name, Size size, double basePrice, CrustType crustType, boolean stuffedCrust, List<Topping> toppings) {
        super(name, size, basePrice);
        this.crustType = crustType;
        this.stuffedCrust = stuffedCrust;
        this.toppings = toppings;
    }

    //getters
    public CrustType getCrustType() {
        return crustType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setStuffedCrust(boolean stuffedCrust) {
        this.stuffedCrust = stuffedCrust;
    }

    public boolean isStuffedCrust() {
        return stuffedCrust;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(String toppingName) {
        toppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
    }

    public boolean canAddExtraCheese() {
        return toppings.stream().noneMatch(t -> t.getName().equalsIgnoreCase("Extra Cheese"));
    }

    public List<Topping> getToppingsByCategory(ToppingCategory category) {
        return toppings.stream().filter(t -> t.getCategory() == category).toList();
    }

    @Override
    public double calculatePrice() {
        double total = getBasePrice();

        //Sum topping prices
        for (Topping t : toppings) {
            total += t.getPrice(getSize());
        }

        if (stuffedCrust)
            total += 2.00;

        return total;
    }

    @Override
    public String toString() {
        String toppingList = toppings.stream()
                .map(t -> t.getName() + " - $" + String.format("%.2f", t.getPrice(getSize())))
                .collect(Collectors.joining(", "));

        String signatureLabel = getName().toLowerCase().contains("signature") ? "(Signature) " : "";

        return String.format("%s%s [%s (\"%s\"), %s crust]\nToppings: [%s]\nPrice: $%.2f",
                signatureLabel,
                getName(),
                getSize(),
                getSize().getInches(),
                crustType,
                toppingList.isEmpty() ? "No toppings" : toppingList,
                calculatePrice());
    }
}
