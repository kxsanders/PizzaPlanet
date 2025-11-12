package org.example;

public class Sides extends Product {
    private String sideName;

    public Sides(String name) {
        //using size small for consistency
        super(name, Size.SMALL, 0.00);
        this.sideName = name;
    }

    @Override
    public double calculatePrice() {
        return 0.00; //included at no cost
    }

    @Override
    public String toString() {
        return sideName + " (Included)";
    }
}
