package org.example;

import java.util.ArrayList;
import java.util.List;

public class SignaturePizzaHelper {

    // ==== Margherita ====
    public static Pizza margherita() {
        Size size = Size.MEDIUM; // 12"
        double base = PricingUtility.getBasePrice(size);
        List<Topping> toppings = new ArrayList<>();

        toppings.add(new Topping("Mozzarella", ToppingCategory.CHEESE, true, false,
                PricingUtility.getCheesePrice(size, false)));
        toppings.add(new Topping("Tomatoes", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Basil", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Marinara", ToppingCategory.SAUCE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Olive Oil", ToppingCategory.SAUCE, false, false,
                PricingUtility.getFreeTopping()));

        return new Pizza("Cosmic Margherita", size, base, CrustType.REGULAR, false, toppings);
    }

    // ==== Veggie ====
    public static Pizza veggie() {
        Size size = Size.SMALL; // 8"
        double base = PricingUtility.getBasePrice(size);
        List<Topping> toppings = new ArrayList<>();

        toppings.add(new Topping("Bell Peppers", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Spinach", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Olives", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Onions", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Marinara", ToppingCategory.SAUCE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Mozzarella", ToppingCategory.CHEESE, true, false,
                PricingUtility.getCheesePrice(size, false)));

        return new Pizza("Nebula Veggie", size, base, CrustType.REGULAR, false, toppings);
    }

    // ==== Supreme ====
    public static Pizza supreme() {
        Size size = Size.MEDIUM; // 12"
        double base = PricingUtility.getBasePrice(size);

        List<Topping> toppings = new ArrayList<>();

        // Premium meats
        toppings.add(new Topping("Pepperoni", ToppingCategory.MEAT, true, false,
                PricingUtility.getMeatPrice(size, false)));
        toppings.add(new Topping("Sausage", ToppingCategory.MEAT, true, false,
                PricingUtility.getMeatPrice(size, false)));

        // Premium cheese
        toppings.add(new Topping("Mozzarella", ToppingCategory.CHEESE, true, false,
                PricingUtility.getCheesePrice(size, false)));

        // Veggie toppings
        toppings.add(new Topping("Onions", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Bell Peppers", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Olives", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));
        toppings.add(new Topping("Mushrooms", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));

        // Sauce
        toppings.add(new Topping("Marinara", ToppingCategory.SAUCE, false, false,
                PricingUtility.getFreeTopping()));

        return new Pizza("Supernova Supreme", size, base, CrustType.REGULAR, false, toppings);
    }


    // ==== Meat Lovers ====
    public static Pizza meatLovers() {
        Size size = Size.MEDIUM; // 12"
        double base = PricingUtility.getBasePrice(size);

        List<Topping> toppings = new ArrayList<>();

        // 4–5 meats (all premium)
        toppings.add(new Topping("Pepperoni", ToppingCategory.MEAT, true, false,
                PricingUtility.getMeatPrice(size, false)));
        toppings.add(new Topping("Sausage", ToppingCategory.MEAT, true, false,
                PricingUtility.getMeatPrice(size, false)));
        toppings.add(new Topping("Ham", ToppingCategory.MEAT, true, false,
                PricingUtility.getMeatPrice(size, false)));
        toppings.add(new Topping("Bacon", ToppingCategory.MEAT, true, false,
                PricingUtility.getMeatPrice(size, false)));
        toppings.add(new Topping("Meatball", ToppingCategory.MEAT, true, false,
                PricingUtility.getMeatPrice(size, false)));

        // Cheese
        toppings.add(new Topping("Mozzarella", ToppingCategory.CHEESE, true, false,
                PricingUtility.getCheesePrice(size, false)));

        // Sauce
        toppings.add(new Topping("Marinara", ToppingCategory.SAUCE, false, false,
                PricingUtility.getFreeTopping()));

        return new Pizza("Meteor Meatstorm", size, base, CrustType.REGULAR, false, toppings);
    }

    // ==== Hawaiian ====
    public static Pizza hawaiian() {
        Size size = Size.MEDIUM; // 12"
        double base = PricingUtility.getBasePrice(size);

        List<Topping> toppings = new ArrayList<>();

        // Premium meat
        toppings.add(new Topping("Ham", ToppingCategory.MEAT, true, false,
                PricingUtility.getMeatPrice(size, false)));

        // Pineapple = regular topping (included)
        toppings.add(new Topping("Pineapple", ToppingCategory.VEGGIE, false, false,
                PricingUtility.getFreeTopping()));

        // Cheese
        toppings.add(new Topping("Mozzarella", ToppingCategory.CHEESE, true, false,
                PricingUtility.getCheesePrice(size, false)));

        // Sauce
        toppings.add(new Topping("Marinara", ToppingCategory.SAUCE, false, false,
                PricingUtility.getFreeTopping()));

        return new Pizza("Lunar Hawaiian", size, base, CrustType.REGULAR, false, toppings);
    }
}
