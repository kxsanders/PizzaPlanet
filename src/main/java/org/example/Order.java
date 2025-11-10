package org.example;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
private int orderId;
private List<Product> products;

    public Order(int orderId, List<Product> products) {
        this.orderId = orderId;
        this.products = products;
    }

    //add product
    public void addProduct (Product product) {
        products.add(product);
    }

    //remove product
    public void removeProduct (String productName) {
        products.removeIf(p -> p.getName().equalsIgnoreCase(productName));
    }

    //calc total
    public double calculateTotal() {
        return  products.stream().mapToDouble(Product::calculatePrice).sum();
    }

    //display summary
    public String displaySummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderId).append("\n");
        sb.append("-------------------------------\n");

        for (Product product : products) {
            sb.append(product.toString()).append("\n\n");
        }

        sb.append("----------------------------------\n");
        sb.append("Total: $").append(String.format("%.2f", calculateTotal())).append("\n");
        sb.append("Time: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return sb.toString();
    }

    //toCSVString
    public String toCSVString() {
        StringBuilder sb = new StringBuilder();

        for (Product p : products) {
            sb.append(orderId).append(",").append(p.getName()).append(",")
                    .append(p.getSize()).append(",").append(p.calculatePrice()).append("\n");
        }
        return sb.toString();
    }

    //getters
    public int getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }
}
