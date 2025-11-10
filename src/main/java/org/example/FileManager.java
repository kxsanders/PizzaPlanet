package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
    /// decided to use BOTH CSV and txt. file to make a singular timestamp
    /// where both saves at the same time but each can be called separately
    /// Assisted by research


    private static final String RECEIPTS_FOLDER = "src/main/resources/receipts/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public void saveOrder(Order order) {
        //Ensure receipts folfer exists
        new File(RECEIPTS_FOLDER).mkdirs();

        String timestamp = LocalDateTime.now().format(FORMATTER);
        String baseFileName = RECEIPTS_FOLDER + timestamp;

        saveOrderAsText(order, baseFileName + ".txt");
        saveOrderAsCSV(order, baseFileName + ".csv");
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
    private void saveOrderAsCSV(Order order, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            //Header
            writer.println("OrderID,ProductType,Name,Size,CrustType,Toppings,StuffedCrust,Quantity,Price");

            for (Product product : order.getProducts()) {

                if (product instanceof Pizza pizza) {
                    String toppings = pizza.getToppings().stream().
                            map(Topping::getName).reduce((a, b) -> a + " | " + b).orElse("");

                    writer.printf("%d,Pizza,%s,%s,%s,\"%s\",%b,,%.2f%n",
                            order.getOrderId(),
                            product.getName(),
                            product.getSize(),
                            pizza.getCrustType(),
                            toppings,
                            pizza.isStuffedCrust(),
                            pizza.calculatePrice()
                    );
                }

                else if (product instanceof Drink drink) {

                    writer.printf("%d,Drink,%s,%s,,,%b,1,%.2f%n",
                            order.getOrderId(),
                            product.getName(),
                            product.getSize(),
                            false,
                            drink.calculatePrice()
                    );
                }

                else if (product instanceof GarlicKnots knots) {

                    writer.printf("%d,GarlicKnots,%s,%s,,,%d,%.2f%n",
                            order.getOrderId(),
                            product.getName(),
                            product.getSize(),
                            knots.getQuantity(),
                            knots.calculatePrice()
                            );
                }
            }

            //footer: total
            writer.printf(",,TOTAL,,,,,,%.2f%n", order.calculateTotal());
            System.out.println("CSV receipt saved: " + filePath);
        }
        catch (IOException e) {
            System.err.println("Error writing to CSV receipt.");
        }
    }
}
