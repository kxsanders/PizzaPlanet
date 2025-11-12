package org.example.JavaSwingUI;

import org.example.Drink;
import org.example.Order;
import org.example.Product;

import javax.swing.*;
import java.awt.*;

public class OrderScreenPanel extends JPanel {

    private final PizzaPlanetApp app;
    private final Order currentOrder;
    private final Runnable onBack;
    private final JLabel totalLabel;
    private final JTextArea orderSummary;

    public OrderScreenPanel(PizzaPlanetApp app, Order order, Runnable onBack) {
        this.app = app;
        this.currentOrder = order;
        this.onBack = onBack;

        setLayout(new BorderLayout());
        setBackground(new Color(15, 18, 40));

        //Header
        JLabel header = new JLabel("Mission Control: Order #" + order.getOrderId(), SwingConstants.CENTER);
        header.setFont(new Font("Orbitron", Font.BOLD, 20));
        header.setForeground(new Color(240, 240, 255));
        header.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        //Order Summary
        orderSummary = new JTextArea(10, 40);
        orderSummary.setEditable(false);
        orderSummary.setBackground(new Color(25, 28, 55));
        orderSummary.setForeground(Color.WHITE);
        orderSummary.setFont(new Font("Monospaced", Font.PLAIN, 14));
        orderSummary.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        add(new JScrollPane(orderSummary), BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(orderSummary);

        //Total
        totalLabel = new JLabel("Total: $0.00", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        totalLabel.setForeground(new Color(162, 102, 255));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 20));

        //Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBackground(new Color(15, 18, 40));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 100, 30, 100));

        JButton addPizza = new JButton("⟡ Add Pizza ⟡ ");
        JButton addDrink = new JButton("⟡ Add Drink ⟡");
        JButton addKnots = new JButton("⟡ Add Garlic Knots ⟡");
        JButton checkout = new JButton("⟡ Complete Mission ⟡");
        JButton cancel = new JButton("⟡ Abort Mission ⟡");
        JButton back = new JButton("⟡ Return to Base ⟡");

        for (JButton b : new JButton[]{addPizza, addDrink, addKnots, checkout, cancel, back}) {
            b.setBackground(new Color(162, 102, 255));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("SansSerif", Font.BOLD, 12));
        }

        buttonPanel.add(addPizza);
        buttonPanel.add(addDrink);
        buttonPanel.add(addKnots);
        buttonPanel.add(checkout);
        buttonPanel.add(cancel);
        buttonPanel.add(back);

        //Layout
        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(15, 18, 40));
        southPanel.add(totalLabel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.CENTER);

        add(southPanel, BorderLayout.SOUTH);

        //ACTIONS
        //addPizza.addActionListener(e -> onAddPizza());
        //addDrink.addActionListener(e -> onAddDrink());
        //addKnots.addActionListener(e -> onAddKnots());
        //checkout.addActionListener(e -> onCheckout());
        //cancel.addActionListener(e -> onCancel());
        //back.addActionListener(e -> onBack.run());
    }

    public void updateSummary() {
        StringBuilder sb = new StringBuilder();

        for (Product p : currentOrder.getProducts()) {
            sb.append(p.toString()).append("\n");
        }

        orderSummary.setText(sb.toString());
        totalLabel.setText(String.format("Total: $%.2f", currentOrder.calculateTotal()));
    }

    //add handlers for each button



}
