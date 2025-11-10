package org.example;

public enum Size {
    SMALL, MEDIUM, LARGE;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
