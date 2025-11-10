package org.example;

import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    private void addPizza(Order order) {
        System.out.println("\nAdd Pizza: ");
        System.out.println("1) Build Your Own");
        System.out.println("2) Signature Pizza");
        System.out.println("0) Cancel");

        System.out.print("Select: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> buildYourOwnPizza(order);
            case "2" -> addSignaturePizza(order);
            case "0" -> System.out.println("Cancelled.");
            default -> System.out.println("Invalid selection.");
        }
    }
    private void addSignaturePizza (Order order) {}

    private void buildYourOwnPizza (Order order) {}
}
