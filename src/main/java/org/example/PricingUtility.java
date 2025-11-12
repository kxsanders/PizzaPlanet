package org.example;

public class PricingUtility {

    // BASE PIZZA PRICE
    public static double getBasePrice(Size size) {
        return switch (size) {
            case SMALL -> 8.50;
            case MEDIUM -> 12.00;
            case LARGE -> 16.50;
        };
    }

    // == MEAT PREMIUM TOPPINGS ==
    public static double getMeatPrice(Size size, boolean isExtra) {

         return switch (size) {
             case SMALL -> isExtra ? 0.50 : 1.00;
             case MEDIUM -> isExtra ? 1.00 : 2.00;
             case LARGE ->  isExtra ? 1.50 : 3.00;
         };
    }

    // == CHEESE ==
    public static double getCheesePrice(Size size, boolean isExtra) {
            return switch (size) {
                case SMALL -> isExtra ? 0.30 : 0.75;
                case MEDIUM -> isExtra ? 0.60 : 1.50;
                case LARGE -> isExtra ? 0.90 : 2.25;
            };
    }

    public static double getRegularToppingPrice() {
        return 0.00;
    }

    // == DRINKS ==
    public static double getDrinkPrice(Size size) {
        return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };
    }

    // == SIDES ==
    public static double getGarlicKnotsPrice() {
        return 1.50;
    }

    // == GENERIC TOPPING CALC ==
    public static double getToppingPrice(Size size, Topping topping) {
        return switch (topping.getCategory()) {
            case MEAT -> getMeatPrice(size, topping.isExtra());
            case CHEESE -> getCheesePrice(size, topping.isExtra());
            default -> 0.00; //regular toppings and sauces
        };
    }
}
