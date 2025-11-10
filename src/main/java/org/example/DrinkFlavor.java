package org.example;

import java.util.Locale;

public enum DrinkFlavor {
    COLA, LEMONADE, WATER, ROOT_BEER, DR_PEPPER;

    @Override
    public String toString() {
        return name().replace("_", " ").toLowerCase();
    }
}
