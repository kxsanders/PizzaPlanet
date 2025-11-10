package org.example;

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

    private static final String RECEIPTS_FOLDER = "src/main/resources/Receipt.csv";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public void saveOrder(Order order) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String baseFileName = RECEIPTS_FOLDER + timestamp;

        saveOrderAsText(order, baseFileName + ".txt");
        saveOrderAsCSV(order, baseFileName + ".csv");
    }

    private void saveOrderAsText(Order order, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("==================================================");
            writer.println("                PIZZA PLANET RECEIPT              ");
            writer.println("==================================================");
            writer.println("Order ID: " + order.getOrderId());
        }
        catch(IOException e){
            System.err.println("Error writing to CSV receipt.");
        }
    }
}
