package org.example;

public class Topping {
private String name;
private ToppingCategory category;
private boolean isExtra;
private double price;

    public Topping(String name, ToppingCategory category, boolean isExtra, Size size) {
        this.name = name;
        this.category = category;
        this.isExtra = isExtra;

        // auto calculate price
        this.price = PricingUtility.getToppingPrice(size, this);
    }

    public String getName() {
        return name;
    }

    public ToppingCategory getCategory() {
        return category;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public double getPrice(Size size) {
        return switch (category) {
            case MEAT -> PricingUtility.getMeatPrice(size, isExtra);
            case CHEESE -> PricingUtility.getCheesePrice(size, isExtra);
            case VEGGIE, SAUCE, OTHER -> PricingUtility.getRegularToppingPrice();
        };
    }

    @Override
    public String toString() {
        return name + (isExtra ? " (extra)" : "") + " - $" + String.format("%.2f", price);
    }


}
