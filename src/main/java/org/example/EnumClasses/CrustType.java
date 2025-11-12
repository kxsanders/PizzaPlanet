package org.example.EnumClasses;

public enum CrustType {
    REGULAR, THIN, THICK, CAULIFLOWER;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase() + " crust";
    }
}
