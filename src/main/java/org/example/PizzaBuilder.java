package org.example;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {

    private Pizza currentPizza;
    private List<Topping> toppings = new ArrayList<>();

    public void startNewPizza(String name, Size size, double basePrice, CrustType crustType, boolean stuffedCrust) {
        toppings.clear(); //reset toppings for a new pizza
        currentPizza = new Pizza(name, size, basePrice, crustType, stuffedCrust, toppings);
    }

    //add topping
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    //remove topping
    public void removeTopping(String toppingName){
        toppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
    }

    //toggle stuffed crust
    public void toggleStuffedCrust() {
        if (currentPizza != null) {
            currentPizza.setStuffedCrust(!currentPizza.isStuffedCrust());
        }
    }

    //check if pizza has cheese
    public boolean hasCheese() {
        return toppings.stream().anyMatch(t -> t.getCategory() == ToppingCategory.CHEESE);
    }

    //can add extra cheese only if it already has cheese
    public boolean canAddExtraCheese() {
        return hasCheese() && toppings.stream()
                .noneMatch(t -> t.getName().equalsIgnoreCase("Extra Cheese"));
    }

    //calculate the current price
    public double calculateCurrentPrice() {
        return currentPizza.calculatePrice();
    }

    //get the built pizza
    public Pizza getPizza() {
        return currentPizza;
    }
}
