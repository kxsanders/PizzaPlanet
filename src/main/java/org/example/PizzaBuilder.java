package org.example;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {

    private Pizza currentPizza;
    private List<Topping> builderToppings = new ArrayList<>();


    // ----------- START A NEW PIZZA (FOR CONSOLE VERSION) -----------
    public void startNewPizza(String name, Size size, double basePrice, CrustType crustType, boolean stuffedCrust) {
        builderToppings.clear();
        currentPizza = new Pizza(name, size, basePrice, crustType, stuffedCrust, builderToppings);
    }


    // ----------- START A NEW PIZZA (FOR SWING VERSION) -----------
    public void startNewPizza(String name, Size size, double basePrice, CrustType crustType, boolean stuffedCrust, List<Topping> toppings) {
        builderToppings = toppings;  // accept external list from Swing
        currentPizza = new Pizza(name, size, basePrice, crustType, stuffedCrust, builderToppings);
    }


    // ----------- TOPPING MANAGEMENT -----------
    public void addTopping(Topping topping) {
        if (currentPizza != null) {
            builderToppings.add(topping);
        }
    }

    public void removeTopping(String toppingName) {
        if (currentPizza != null) {
            builderToppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
        }
    }


    // ----------- STUFFED CRUST -----------
    public void toggleStuffedCrust() {
        if (currentPizza != null) {
            currentPizza.setStuffedCrust(!currentPizza.isStuffedCrust());
        }
    }


    // ----------- CHEESE CHECK LOGIC -----------
    public boolean hasCheese() {
        return currentPizza != null &&
                builderToppings.stream().anyMatch(t -> t.getCategory() == ToppingCategory.CHEESE);
    }

    public boolean canAddExtraCheese() {
        if (currentPizza == null) return false;

        boolean hasCheese = builderToppings.stream()
                .anyMatch(t -> t.getCategory() == ToppingCategory.CHEESE);

        boolean hasExtraCheese = builderToppings.stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase("Extra Cheese"));

        return hasCheese && !hasExtraCheese;
    }

    // ----------- PRICE CALCULATION -----------
    public double calculateCurrentPrice() {
        return currentPizza != null ? currentPizza.calculatePrice() : 0.0;
    }

    // ----------- GET FINAL PIZZA -----------
    public Pizza getPizza() {
        return currentPizza;
    }
}

