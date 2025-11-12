package org.example.EnumClasses;

public enum Size {
    SMALL(8, 2.00),
    MEDIUM(12, 2.50),
    LARGE(16, 3.00);

    private final int inches;
    private final double drinkPrice;

    Size(int inches, double drinkPrice) {
        this.inches = inches;
        this.drinkPrice = drinkPrice;
    }

    //PIZZA DISPLAY
    public String pizzaLabel() {
        return name().charAt(0) + name().substring(1).toLowerCase() + " (" + inches + "\")";
    }

    //DRINK DISPLAY
    public String drinkLabel() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    //used in the drink pricing
    public double getDrinkPrice() {
        return drinkPrice;
    }

    public int getInches() {
        return inches;
    }
}

