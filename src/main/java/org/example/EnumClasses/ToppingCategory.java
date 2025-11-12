package org.example.EnumClasses;

public enum ToppingCategory {
    MEAT, VEGGIE, CHEESE, SAUCE, OTHER;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
