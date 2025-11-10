package org.example;

public enum CrustType {
    THIN, HAND_TOSSED, DEEP_DISH, GLUTEN_FREE;

    @Override
    public String toString() {
        return name().replace("_", " ").toLowerCase();
    }
}
