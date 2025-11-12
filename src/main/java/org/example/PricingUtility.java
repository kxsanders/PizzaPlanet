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

    // == MEAT ==
    public static double getMeatPrice(Size size, boolean extra) {
      if (!extra) {
         return switch (size) {
             case SMALL ->  1.00;
             case MEDIUM -> 2.00;
             case LARGE ->  3.00;
         };
      }
      else {
          return switch (size) {
              case SMALL -> 0.50;
              case MEDIUM -> 1.00;
              case LARGE ->  1.50;
          };
      }
    }

    // == CHEESE ==
    public static double getCheesePrice(Size size, boolean extra) {
        if (!extra) {
            return switch (size) {
                case SMALL -> 0.75;
                case MEDIUM -> 1.50;
                case LARGE -> 2.25;
            };
        }
        else {
            return switch (size) {
                case SMALL -> 0.30;
                case MEDIUM -> 0.60;
                case LARGE -> 0.90;
            };
        }
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
