package org.example.JavaSwingUI;

import org.example.*;

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
        orderSummary.setLineWrap(true);
        orderSummary.setWrapStyleWord(true);
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
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        buttonPanel.setBackground(new Color(15, 18, 40));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 100, 30, 100));

        JButton addPizza = new JButton("⟡ Add Pizza ⟡ ");
        JButton addDrink = new JButton("⟡ Add Drink ⟡");
        JButton addKnots = new JButton("⟡ Add Garlic Knots ⟡");
        JButton addSides = new JButton("⟡ Add Sides ⟡");
        JButton checkout = new JButton("⟡ Complete Mission ⟡");
        JButton cancel = new JButton("⟡ Abort Mission ⟡");
        JButton back = new JButton("⟡ Return to Base ⟡");

        for (JButton b : new JButton[]{addPizza, addDrink, addKnots, addSides, checkout, cancel, back}) {
            b.setBackground(new Color(162, 102, 255));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("SansSerif", Font.BOLD, 12));
        }

        buttonPanel.add(addPizza);
        buttonPanel.add(addDrink);
        buttonPanel.add(addKnots);
        buttonPanel.add(addSides);
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
        addPizza.addActionListener(e -> onAddPizza());
        addDrink.addActionListener(e -> onAddDrink());
        addKnots.addActionListener(e -> onAddKnots());
        addSides.addActionListener(e -> showSidesDialog());
        checkout.addActionListener(e -> onCheckout());
        cancel.addActionListener(e -> onCancel());
        back.addActionListener(e -> onBack.run());
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
    private void onAddPizza() {

        String[] modes = {"Build your Own", "Signature Pizzas"};
        String mode = (String) JOptionPane.showInputDialog(
                this, "Choose pizza mode:",
                "Add Pizza", JOptionPane.QUESTION_MESSAGE,
                null, modes, modes[0]);

        if (mode == null) return;

        // --- Signature Pizzas ---
        if (mode.equals("Signature Pizzas")) {
            String[] sig = {"Cosmic Margherita", "Nebula Veggie", "Supernova Supreme",
                    "Meteor Meatstorm", "Lunar Hawaiian"};

            String pick = (String) JOptionPane.showInputDialog(
                    this, "Choose your signature pizza:",
                    "Signature Pizzas", JOptionPane.QUESTION_MESSAGE,
                    null, sig, sig[0]);

            if (pick == null) return;

            Pizza pizza = switch (pick) {
                case "Cosmic Margherita" -> SignaturePizzaHelper.margherita();
                case "Nebula Veggie" -> SignaturePizzaHelper.veggie();
                case "Supernova Supreme" -> SignaturePizzaHelper.supreme();
                case "Meteor Meatstorm" -> SignaturePizzaHelper.meatLovers();
                case "Lunar Hawaiian" -> SignaturePizzaHelper.hawaiian();
                default -> null;
            };

            if (pizza != null) {
                currentOrder.addProduct(pizza);
                updateSummary();
            }
            return;
        }

        // --- Build Your Own ---
        app.showAddPizzaScreen(currentOrder);
    }

    private void onAddDrink() {
        org.example.DrinkFlavor[] flavors = {
                DrinkFlavor.COLA,
                DrinkFlavor.LEMONADE,
                DrinkFlavor.WATER,
                DrinkFlavor.ROOT_BEER,
                DrinkFlavor.DR_PEPPER
        };

        org.example.DrinkFlavor flavor = (org.example.DrinkFlavor) JOptionPane.showInputDialog(
                this, "Choose flavor:", "Add Drink", JOptionPane.QUESTION_MESSAGE,
                null, flavors, DrinkFlavor.COLA);

        if (flavor == null)
            return;

        org.example.Size size = (org.example.Size) JOptionPane.showInputDialog(
                this, "Choose size:", "Add Drink", JOptionPane.QUESTION_MESSAGE,
                null, org.example.Size.values(), Size.MEDIUM);

        if (size == null)
            return;

        double price = switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };

        org.example.Drink drink = new org.example.Drink(flavor.toString(), size, price, flavor);
        currentOrder.addProduct(drink);
        updateSummary();
    }

    private void onAddKnots() {
        String qtyKnots = JOptionPane.showInputDialog(this, "How many orders of Garlic Knots would you like?");

        if (qtyKnots == null)
            return;

        int qty;
        try {
            qty = Integer.parseInt(qtyKnots.trim());
        }
        catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Invalid number.");
            return;
        }

        if (qty <= 0)
            return;

        org.example.GarlicKnots knots = new org.example.GarlicKnots(qty);
        currentOrder.addProduct(knots);
        updateSummary();
    }

    private void showSidesDialog() {
        JCheckBox redPepperBox = new JCheckBox("Red Pepper (Included)");
        JCheckBox parmesanBox = new JCheckBox("Parmesan (Included)");

        redPepperBox.setBackground(new Color(15, 18, 40));
        redPepperBox.setForeground(Color.WHITE);
        parmesanBox.setBackground(new Color(15, 18, 40));
        parmesanBox.setForeground(Color.WHITE);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(new Color(15, 18, 40));
        panel.add(redPepperBox);
        panel.add(parmesanBox);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Choose your Complimentary Sides", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            if(redPepperBox.isSelected()) {
                currentOrder.addProduct(new Sides("Red Pepper"));
            }
            if (parmesanBox.isSelected()) {
                currentOrder.addProduct(new Sides("Parmesan"));
            }

            updateSummary();
        }
    }

    private void onCheckout() {

        long pizzas = currentOrder.getProducts().stream()
                .filter(p -> p instanceof org.example.Pizza)
                .count();

        long others = currentOrder.getProducts().size() - pizzas;

        // Business rule: can't checkout with nothing
        if (pizzas == 0 && others == 0) {
            JOptionPane.showMessageDialog(this,
                    "You must add at least one item to complete your mission.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Complete mission and save receipt?",
                "Confirm Checkout",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION)
            return;

        // --- SAVE ORDER ---
        try {
            org.example.FileManager.saveOrder(currentOrder);

            JOptionPane.showMessageDialog(this,
                    "Mission Complete! Receipts generated successfully.");

            // Go back home
            onBack.run();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error saving receipt: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        int confirmCancel = JOptionPane.showConfirmDialog(this,
                "Abort mission and discard this order?", "Cancel Order", JOptionPane.YES_NO_OPTION);
        if (confirmCancel == JOptionPane.YES_OPTION)
            onBack.run();
    }

}
