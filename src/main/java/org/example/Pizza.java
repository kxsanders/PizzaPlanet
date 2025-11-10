package org.example;

import java.util.List;

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
        for (Topping t : toppings) {
            total += t.getPrice();
        }
        if (stuffedCrust) total += 2.00;
        return total;
    }

    @Override
    public String toString() {
        return  getName() + " Pizza (" + getSize() + ", " + crustType + " crust)" + (stuffedCrust ? " [Stuffed Crust]" : "") +
                "\nToppings: " + toppings + "\nPrice: $" + calculatePrice();
    }
}
