package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {

    private static final String RECEIPTS_FOLDER = "src/main/resources/receipts/";
    private static final String MASTER_CSV = RECEIPTS_FOLDER + "receipt.csv";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public void saveOrder(Order order) {

        //Ensure folder exists
        new File(RECEIPTS_FOLDER).mkdirs();

        String timestamp = LocalDateTime.now().format(FORMATTER);
        String txtFile = RECEIPTS_FOLDER + timestamp + ".txt";

        saveOrderAsText(order, txtFile);
        saveOrderAsCSV(order);
    }

    //save the txt file
    private void saveOrderAsText(Order order, String filePath) {
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
            writer.println("Thank you for choosing Pizza Planet!");
            writer.println("==================================================");

            System.out.println("Text receipt saved: " + filePath);

        }
        catch(IOException e){
            System.err.println("Error writing to TXT receipt.");
        }
    }

    //save the CSV file
    private void saveOrderAsCSV(Order order) {


        boolean needsHeader = !new File(MASTER_CSV).exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(MASTER_CSV, true))) {

            if (needsHeader) {
                writer.println("OrderID | ProductType | Name | Size | CrustType | Toppings | StuffedCrust | Qty | Price");
            }

            for (Product product : order.getProducts()) {

                // ---------- PIZZA ----------
                if (product instanceof Pizza pizza) {

                    String toppings = pizza.getToppings().stream()
                            .map(Topping::getName)
                            .reduce((a, b) -> a + " | " + b)
                            .orElse("");

                    writer.printf("%d,Pizza,%s,%s,%s,\"%s\",%b,,%.2f%n",
                            order.getOrderId(),      // OrderID
                            product.getName(),       // Name
                            product.getSize(),       // Size
                            pizza.getCrustType(),    // Crust
                            toppings,                // Toppings
                            pizza.isStuffedCrust(),  // Stuffed
                            pizza.calculatePrice()   // Price
                    );
                }

                // ---------- DRINK ----------
                else if (product instanceof Drink drink) {

                    writer.printf("%d,Drink,%s,%s,,, ,1,%.2f%n",
                            order.getOrderId(),      // OrderID
                            product.getName(),       // Name
                            product.getSize(),       // Size
                            drink.calculatePrice()   // Price
                    );
                }

                // ---------- GARLIC KNOTS ----------
                else if (product instanceof GarlicKnots knots) {

                    writer.printf("%d,GarlicKnots,%s,,,,,%d,%.2f%n",
                            order.getOrderId(),      // OrderID
                            product.getName(),       // Name
                            knots.getQuantity(),     // Quantity
                            knots.calculatePrice()   // Price
                    );
                }
            }

            // total row
            writer.printf(",,TOTAL,,,,,,%.2f%n", order.calculateTotal());
            System.out.println("CSV receipt saved: " + MASTER_CSV);

        } catch (IOException e) {
            System.err.println("Error writing to CSV receipt.");
        }
    }
}
