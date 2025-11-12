package org.example.EnumClasses;

public enum DrinkFlavor {
    COLA, LEMONADE, WATER, ROOT_BEER, DR_PEPPER;

    @Override
    public String toString() {
        String formatted = name().replace("_", " ").toLowerCase();
        return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
    }
}
