package org.example;

import java.util.List;

public class SignaturePizzaHelper {

    // ==== Margherita ====
    public static SignaturePizza margherita() {
        Size size = Size.MEDIUM;

        return new SignaturePizza("Margherita", size, PricingUtility.getBasePrice(size),
                CrustType.REGULAR, false, List.of(
                new Topping("Mozzarella", ToppingCategory.CHEESE, false, size),
                new Topping("Tomatoes", ToppingCategory.VEGGIE, false, size),
                new Topping("Basil", ToppingCategory.VEGGIE, false, size),
                new Topping("Marinara", ToppingCategory.SAUCE, false, size),
                new Topping("Olive Oil", ToppingCategory.SAUCE, false, size)
            )
        );
    }

    // ==== Veggie ====
    public static SignaturePizza veggie() {
        Size size = Size.SMALL;

        return new SignaturePizza("Veggie", size, PricingUtility.getBasePrice(size),
                CrustType.REGULAR, false,List.of(
                new Topping("Bell Peppers", ToppingCategory.VEGGIE, false, size),
                new Topping("Spinach", ToppingCategory.VEGGIE, false, size),
                new Topping("Olives", ToppingCategory.VEGGIE, false, size),
                new Topping("Onions", ToppingCategory.VEGGIE, false, size),
                new Topping("Marinara", ToppingCategory.SAUCE, false, size),
                new Topping("Mozzarella", ToppingCategory.CHEESE, false, size)
            )
        );
    }

    //add more signature pizzas
}
