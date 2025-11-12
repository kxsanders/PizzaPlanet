package org.example;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {

    private Pizza currentPizza;
    private List<Topping> toppings = new ArrayList<>();

    public void startNewPizza(String name, Size size, double basePrice, CrustType crustType, boolean stuffedCrust, List<Topping> toppings) {
        toppings.clear(); //reset toppings for a new pizza
        currentPizza = new Pizza(name, size, basePrice, crustType, stuffedCrust, toppings);
    }

    //overload method to allow console version and Swing version
    public void startNewPizza(String name, Size size, double basePrice, CrustType crustType, boolean stuffedCrust) {
        startNewPizza(name, size, basePrice, crustType, stuffedCrust, new ArrayList<>()); // default toppings list
    }


    //add topping
    public void addTopping(Topping topping) {
        if (currentPizza != null) {
            currentPizza.addTopping(topping);
        }
    }

    //remove topping
    public void removeTopping(String toppingName){
        if (currentPizza != null) {
            currentPizza.removeTopping(toppingName);
        }
    }

    //toggle stuffed crust
    public void toggleStuffedCrust() {
        if (currentPizza != null) {
            currentPizza.setStuffedCrust(!currentPizza.isStuffedCrust());
        }
    }

    //check if pizza has cheese
    public boolean hasCheese() {
        if (currentPizza == null) return false;
        return currentPizza.getToppings().stream()
                .anyMatch(t -> t.getCategory() == ToppingCategory.CHEESE);
    }

    //can add extra cheese only if it already has cheese
    public boolean canAddExtraCheese() {
        if (currentPizza == null) return false;

        boolean hasCheese = currentPizza.getToppings().stream()
                .anyMatch(t -> t.getCategory() == ToppingCategory.CHEESE);

        boolean hasExtraCheese = currentPizza.getToppings().stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase("Extra Cheese"));

        return hasCheese && !hasExtraCheese;
    }

    //calculate the current price
    public double calculateCurrentPrice() {
        if (currentPizza == null) return 0.0;
        return currentPizza.calculatePrice();
    }

    //get the built pizza
    public Pizza getPizza() {
        return currentPizza;
    }
}
