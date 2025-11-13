package org.example;

public class PricingUtility {

    // Base crust price by size
    public static double getBasePrice(Size size) {
        return switch (size) {
            case SMALL -> 8.50;   // 8"
            case MEDIUM -> 12.00; // 12"
            case LARGE -> 16.50;  // 16"
        };
    }

    // Meat (premium)
    public static double getMeatPrice(Size size, boolean extra) {
        // full price for this topping (regular or extra)
        return switch (size) {
            case SMALL -> extra ? 1.00 + 0.50 : 1.00;
            case MEDIUM -> extra ? 2.00 + 1.00 : 2.00;
            case LARGE -> extra ? 3.00 + 1.50 : 3.00;
        };
    }

    // Cheese (premium)
    public static double getCheesePrice(Size size, boolean extra) {
        return switch (size) {
            case SMALL -> extra ? 0.75 + 0.30 : 0.75;
            case MEDIUM -> extra ? 1.50 + 0.60 : 1.50;
            case LARGE -> extra ? 2.25 + 0.90 : 2.25;
        };
    }

    // Regular toppings & sauces & included sides
    public static double getFreeTopping() {
        return 0.00;
    }

    // Drinks
    public static double getDrinkPrice(Size size) {
        return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };
    }

    public static double getGarlicKnotsPrice() {
        return 1.50;
    }
}

