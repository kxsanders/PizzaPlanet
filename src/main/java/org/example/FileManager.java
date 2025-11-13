package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class FileManager {

    private static final String RECEIPTS_FOLDER = "src/main/resources/Receipts/";
    private static final String MASTER_CSV = RECEIPTS_FOLDER + "Receipt.csv";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    // Public method called by Swing checkout.
    public static void saveOrder(Order order) throws IOException {

        // Ensure folder exists
        File folder = new File(RECEIPTS_FOLDER);
        if (!folder.exists()) folder.mkdirs();

        // Generate timestamp
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String txtFilePath = RECEIPTS_FOLDER + timestamp + ".txt";

        // Save both formats
        saveOrderAsText(order, txtFilePath);
        saveOrderAsCSV(order);
    }

    //Saves receipt as a TXT file.
    private static void saveOrderAsText(Order order, String filePath) throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            writer.println("==================================================");
            writer.println("                PIZZA PLANET RECEIPT              ");
            writer.println("==================================================");
            writer.println("Order ID: " + order.getOrderId());
            writer.println("Date: " + LocalDateTime.now());
            writer.println("--------------------------------------------------");

            for (Product product : order.getProducts()) {
                writer.println(product.toString());
                writer.println();
            }

            writer.println("--------------------------------------------------");
            writer.printf("TOTAL: $%.2f%n", order.calculateTotal());
            writer.println("==================================================");
            writer.println("   Thank you for choosing Pizza Planet!");
            writer.println("==================================================");

            System.out.println("TXT receipt saved");
        }
    }

    //Saves receipt details to receipts.csv in pipe format.
    private static void saveOrderAsCSV(Order order) throws IOException {

        boolean needsHeader = !new File(MASTER_CSV).exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(MASTER_CSV, true))) {

            // Write header once
            if (needsHeader) {
                writer.println("OrderID|ProductType|Name|Size|CrustType|Toppings|StuffedCrust|Qty|Price");
            }

            // LOOP THROUGH ITEMS
            for (Product product : order.getProducts()) {

                // ==== PIZZA ====
                if (product instanceof Pizza pizza) {

                    String toppings = pizza.getToppings().stream()
                            .map(Topping::getName)
                            .collect(Collectors.joining(","));

                    writer.printf("%d|Pizza|%s|%s|%s|%s|%b|1|%.2f%n",
                            order.getOrderId(),
                            pizza.getName(),
                            pizza.getSize(),
                            pizza.getCrustType(),
                            toppings,
                            pizza.isStuffedCrust(),
                            pizza.calculatePrice()
                    );
                }

                // ==== DRINK ====
                else if (product instanceof Drink drink) {
                    writer.printf("%d|Drink|%s|%s||||1|%.2f%n",
                            order.getOrderId(),
                            drink.getName(),
                            drink.getSize(),
                            drink.calculatePrice()
                    );
                }

                // ==== GARLIC KNOTS ====
                else if (product instanceof GarlicKnots knots) {
                    writer.printf("%d|GarlicKnots|Garlic Knots||| | |%d|%.2f%n",
                            order.getOrderId(),
                            knots.getQuantity(),
                            knots.calculatePrice()
                    );
                }

                // ==== SIDES ====
                else if (product instanceof Sides side) {
                    writer.printf("%d|Side|%s||||1|0.00%n",
                            order.getOrderId(),
                            side.getName()
                    );
                }
            }

            System.out.println("CSV receipt saved → " + MASTER_CSV);
        }
    }
}
